package br.com.api_order.domain.use_case.order;

import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.enums.StatusOrder;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.use_case.order.UpdateOrderStatusImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateOrderStatusTest {

    @Mock
    private OrderPersistence orderPersistence;

    @Mock
    private FindOrderById findOrderById;

    @InjectMocks
    private UpdateOrderStatusImpl updateOrderStatus;

    @Test
    void shouldUpdateOrderStatusSuccessfully() {
        UUID orderId = UUID.randomUUID();
        StatusOrder newStatus = StatusOrder.RECEIVED;
        OrderDomain mockOrder = new OrderDomain();
        mockOrder.setId(orderId);
        mockOrder.setStatus(StatusOrder.RECEIVED);

        when(findOrderById.execute(orderId)).thenReturn(mockOrder);

        updateOrderStatus.execute(orderId, newStatus);

        assertEquals(newStatus, mockOrder.getStatus());
        verify(findOrderById, times(1)).execute(orderId);
        verify(orderPersistence, times(1)).save(mockOrder);
    }

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {
        UUID orderId = UUID.randomUUID();
        StatusOrder newStatus = StatusOrder.CANCELED;

        when(findOrderById.execute(orderId)).thenThrow(new RuntimeException("Order not found"));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                updateOrderStatus.execute(orderId, newStatus)
        );

        assertEquals("Order not found", exception.getMessage());
        verify(findOrderById, times(1)).execute(orderId);
        verify(orderPersistence, never()).save(any());
    }

}