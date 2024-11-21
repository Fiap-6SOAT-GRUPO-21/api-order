package br.com.api_order.domain.use_case.order.item;

import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.persistence.order.item.OrderItemPersistence;
import br.com.api_order.use_case.order.item.CreateNewOrderItemImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateNewOrderItemTest {

    @Mock
    private OrderItemPersistence orderItemPersistence;

    @InjectMocks
    private CreateNewOrderItemImpl createNewOrderItemImpl;

    private OrderItemDomain orderItemDomain;

    @BeforeEach
    void setUp() {
        orderItemDomain = new OrderItemDomain();
        orderItemDomain.setId(UUID.randomUUID());
    }

    @Test
    void testExecute_shouldSaveOrderItem() {
        when(orderItemPersistence.save(any(OrderItemDomain.class))).thenReturn(orderItemDomain);

        OrderItemDomain result = createNewOrderItemImpl.execute(orderItemDomain);

        assertEquals(orderItemDomain, result);
    }

}