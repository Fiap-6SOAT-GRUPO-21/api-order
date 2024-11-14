package br.com.api_order.useCases.order;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.enums.StatusOrder;
import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.useCases.customer.FindCustomerByCPF;
import br.com.api_order.domain.useCases.order.FindOrderById;
import br.com.api_order.domain.useCases.order.UpdateOrder;
import br.com.api_order.domain.useCases.order.item.CreateNewOrderItem;
import br.com.api_order.domain.useCases.product.FindProductById;
import br.com.api_order.domain.useCases.product.FindProductByIdAndIdStore;
import br.com.api_order.useCases.order.exceptions.OrderStatusNotReceived;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UpdateOrderImpl implements UpdateOrder {

    private final FindOrderById findOrderById;
    private final FindCustomerByCPF findCustomerByCPF;
    private final OrderPersistence orderPersistence;
    private final FindProductById findProductById;
    private final FindProductByIdAndIdStore findProductByIdAndIdStore;
    private final CreateNewOrderItem createNewOrderItem;

    @Override
    public OrderDomain execute(UUID idOrder, String cpf, List<OrderItemDomain> items, PaymentType provider) {

        OrderDomain orderDomain = findOrderById.execute(idOrder);
        orderDomain.validatedQuantityItems(items);

        if (!orderDomain.getStatus().equals(StatusOrder.RECEIVED))
            throw new OrderStatusNotReceived();

        orderDomain.validatedItemOrException(findProductByIdAndIdStore);

        items.forEach(item -> {
            Optional<OrderItemDomain> optionalOrderItemDomain = findOrderItemDomain(orderDomain, item);

            optionalOrderItemDomain.ifPresentOrElse(
                    existingItem -> existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity()),
                    () -> orderDomain.getItems().add(item));
        });

        if (cpf != null) {
            CustomerDTO customerDomain = findCustomerByCPF.execute(cpf);
            orderDomain.setIdCustomer(customerDomain.getId());
        }

        orderDomain.setStatus(StatusOrder.RECEIVED);

        orderDomain.setIdPayment(null);

        orderDomain.calculateTotal(findProductById);

        OrderDomain orderDomainSave = orderPersistence.save(orderDomain);

        List<OrderItemDomain> savedItems = orderDomain.getItems().stream()
                .map(item -> {
                    item.setIdOrder(orderDomainSave.getId());
                    item.setOrder(orderDomainSave);
                    return createNewOrderItem.execute(item);
                })
                .collect(Collectors.toList());

        orderDomainSave.setItems(savedItems);
        return orderPersistence.save(orderDomainSave);
    }

    private Optional<OrderItemDomain> findOrderItemDomain(OrderDomain orderDomain, OrderItemDomain item) {
        return orderDomain.getItems().stream()
                .filter(orderItem -> orderItem.getIdProduct().equals(item.getIdProduct()))
                .findFirst();
    }
}
