package br.com.api_order.domain.use_case.order;

import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.use_case.order.FindOrderByIdImpl;
import br.com.api_order.use_case.order.exceptions.OrderNotFound;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FindOrdersByCPFTest {

    @Mock
    private OrderPersistence orderPersistence;

    @InjectMocks
    private FindOrderByIdImpl findOrderById;

    private UUID orderId;
    private OrderDomain order;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        order = new OrderDomain();
    }

    @Test
    void execute_shouldReturnOrder_whenOrderExists() {
        when(orderPersistence.findById(orderId)).thenReturn(Optional.of(order));

        OrderDomain result = findOrderById.execute(orderId);

        assertNotNull(result);
        assertEquals(order, result);
        verify(orderPersistence, times(1)).findById(orderId);
    }

    @Test
    void execute_shouldThrowOrderNotFound_whenOrderDoesNotExist() {
        when(orderPersistence.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFound.class, () -> findOrderById.execute(orderId));
        verify(orderPersistence, times(1)).findById(orderId);
    }

}