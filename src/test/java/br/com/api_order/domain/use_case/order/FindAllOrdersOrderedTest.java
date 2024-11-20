package br.com.api_order.domain.use_case.order;

import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.use_case.order.FindAllOrdersOrderedImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindAllOrdersOrderedTest {

    @Mock
    private OrderPersistence orderPersistence;

    @InjectMocks
    private FindAllOrdersOrderedImpl findAllOrdersOrdered;

    @Test
    void execute_shouldReturnOrderedOrders() {
        List<OrderDomain> expectedOrders = Arrays.asList(new OrderDomain(), new OrderDomain());
        when(orderPersistence.findAllOrdered()).thenReturn(expectedOrders);

        List<OrderDomain> actualOrders = findAllOrdersOrdered.execute();

        assertEquals(expectedOrders, actualOrders);
    }

}