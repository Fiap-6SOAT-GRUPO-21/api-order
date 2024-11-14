package br.com.api_order.useCases.order;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.application.dtos.order.response.OrderResponse;
import br.com.api_order.application.dtos.payment.PaymentDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.enums.StatusOrder;
import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.useCases.customer.FindCustomerByCPF;
import br.com.api_order.domain.useCases.order.CreateNewOrder;
import br.com.api_order.domain.useCases.order.item.CreateNewOrderItem;
import br.com.api_order.domain.useCases.payment.MakeANewPayment;
import br.com.api_order.domain.useCases.payment.ProcessPayment;
import br.com.api_order.domain.useCases.product.FindProductById;
import br.com.api_order.domain.useCases.product.FindProductByIdAndIdStore;
import br.com.api_order.domain.useCases.store.FindStoreById;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreateNewOrderImpl implements CreateNewOrder {

    private final OrderPersistence orderPersistence;
    private final CreateNewOrderItem createNewOrderItem;
    private final FindStoreById findStoreById;
    private final FindProductById findProductById;
    private final FindProductByIdAndIdStore findProductByIdAndIdStore;
    private final MakeANewPayment makeANewPayment;
    private final FindCustomerByCPF findCustomerByCPF;
    private final Map<String, ProcessPayment> processPaymentList;
    private final ModelMapper modelMapper;

    @Override
    public OrderResponse execute(OrderDomain orderDomain, String cpf, PaymentType provider) {
        orderDomain.validatedStore(findStoreById);
        orderDomain.validatedQuantityItems(orderDomain.getItems());
        orderDomain.validatedItemOrException(findProductByIdAndIdStore);

        if (cpf != null) {
            CustomerDTO customerDomain = findCustomerByCPF.execute(cpf);
            orderDomain.setIdCustomer(customerDomain.getId());
        }

        orderDomain.calculateTotal(findProductById);

        orderDomain.setStatus(StatusOrder.RECEIVED);

        OrderDomain orderDomainSave = orderPersistence.save(orderDomain);

        List<OrderItemDomain> savedItems = orderDomain.getItems().stream()
                .map(item -> {
                    item.setIdOrder(orderDomainSave.getId());
                    item.setOrder(orderDomainSave);
                    return createNewOrderItem.execute(item);
                })
                .collect(Collectors.toList());

        orderDomainSave.setItems(savedItems);

        ProcessPayment processPayment = processPaymentList.get(provider.name());
        if (processPayment == null)
            throw new IllegalArgumentException("Invalid payment provider: " + provider);

        PaymentDTO payment = makeANewPayment.execute(orderDomainSave, provider, processPayment);
        orderDomainSave.setIdPayment(payment.getPaymentId());

        OrderDomain savedOrder = orderPersistence.save(orderDomainSave);
        OrderResponse orderResponse = modelMapper.map(savedOrder, OrderResponse.class);
        orderResponse.setQrCode(payment.getQrCode());
        orderResponse.setPaymentType(payment.getType());
        orderResponse.setPaymentStatus(payment.getStatus());
        orderResponse.setIdCustomer(savedOrder.getIdCustomer());

        return orderResponse;
    }
}