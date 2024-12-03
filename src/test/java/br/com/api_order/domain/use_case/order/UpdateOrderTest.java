package br.com.api_order.domain.use_case.order;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.enums.StatusOrder;
import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.use_case.customer.FindCustomerByCPF;
import br.com.api_order.domain.use_case.order.item.CreateNewOrderItem;
import br.com.api_order.domain.use_case.product.FindProductById;
import br.com.api_order.domain.use_case.product.FindProductByIdAndIdStore;
import br.com.api_order.use_case.order.UpdateOrderImpl;
import br.com.api_order.use_case.order.exceptions.OrderStatusNotReceived;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateOrderTest {

    @Mock
    private FindOrderById findOrderById;

    @Mock
    private FindCustomerByCPF findCustomerByCPF;

    @Mock
    private OrderPersistence orderPersistence;

    @Mock
    private FindProductById findProductById;

    @Mock
    private FindProductByIdAndIdStore findProductByIdAndIdStore;

    @Mock
    private CreateNewOrderItem createNewOrderItem;

    @InjectMocks
    private UpdateOrderImpl updateOrder;

//    @Test
//    void shouldUpdateOrderSuccessfully() {
//        UUID idOrder = UUID.randomUUID();
//        String cpf = "12345678900";
//        OrderItemDomain item = new OrderItemDomain();
//        item.setId(UUID.randomUUID());
//        item.setIdProduct(UUID.randomUUID());
//        item.setQuantity(5);
//
//        OrderDomain existingOrder = new OrderDomain();
//        existingOrder.setId(idOrder);
//        existingOrder.setStatus(StatusOrder.RECEIVED);
//        existingOrder.setItems(List.of(item));
//
//        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(UUID.randomUUID());
//
//        when(findOrderById.execute(idOrder)).thenReturn(existingOrder);
//        when(findCustomerByCPF.execute(cpf)).thenReturn(customerDTO);
//        when(orderPersistence.save(any(OrderDomain.class))).thenReturn(existingOrder);
//        when(createNewOrderItem.execute(any(OrderItemDomain.class))).thenReturn(item);
//
//        OrderDomain updatedOrder = updateOrder.execute(idOrder, cpf, List.of(item), PaymentType.MERCADO_PAGO);
//
//        assertEquals(StatusOrder.RECEIVED, updatedOrder.getStatus());
//        assertEquals(customerDTO.getId(), updatedOrder.getIdCustomer());
//        verify(findOrderById, times(1)).execute(idOrder);
//        verify(findCustomerByCPF, times(1)).execute(cpf);
//        verify(orderPersistence, times(2)).save(any(OrderDomain.class));
//        verify(createNewOrderItem, times(1)).execute(any(OrderItemDomain.class));
//    }

    @Test
    void shouldThrowExceptiddsonWhenOrderStatusIsNotReceived() {
        UUID idOrder = UUID.randomUUID();

        OrderItemDomain processedItem = new OrderItemDomain();
        processedItem.setId(UUID.randomUUID());
        processedItem.setIdProduct(UUID.randomUUID());
        processedItem.setQuantity(5);

        OrderDomain existingOrder = new OrderDomain();
        existingOrder.setId(idOrder);
        existingOrder.setStatus(StatusOrder.IN_PREPARATION);
        existingOrder.setItems(List.of(processedItem));

        when(findOrderById.execute(idOrder)).thenReturn(existingOrder);

        assertThrows(OrderStatusNotReceived.class,
                () -> updateOrder.execute(idOrder, null, List.of(processedItem), PaymentType.MERCADO_PAGO)
        );
        verify(findOrderById, times(1)).execute(idOrder);
        verifyNoInteractions(findCustomerByCPF, createNewOrderItem, orderPersistence);
    }

}