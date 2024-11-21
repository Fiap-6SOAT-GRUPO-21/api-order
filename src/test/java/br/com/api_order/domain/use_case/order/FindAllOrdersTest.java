package br.com.api_order.domain.use_case.order;

import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.use_case.order.FindAllOrdersImpl;
import org.junit.jupiter.api.BeforeEach;
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
class FindAllOrdersTest {

    @Mock
    private OrderPersistence orderPersistence;

    @InjectMocks
    private FindAllOrdersImpl findAllOrders;

    private List<OrderDomain> orderList;

    @BeforeEach
    void setUp() {
        OrderDomain order1 = new OrderDomain();
        OrderDomain order2 = new OrderDomain();
        orderList = Arrays.asList(order1, order2);
    }

    @Test
    void testExecute() {
        when(orderPersistence.findAll()).thenReturn(orderList);

        List<OrderDomain> result = findAllOrders.execute();

        assertEquals(orderList, result);
    }

}