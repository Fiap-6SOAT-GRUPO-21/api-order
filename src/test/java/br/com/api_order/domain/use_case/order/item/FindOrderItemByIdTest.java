package br.com.api_order.domain.use_case.order.item;

import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.persistence.order.item.OrderItemPersistence;
import br.com.api_order.use_case.order.exceptions.OrderNotFound;
import br.com.api_order.use_case.order.item.FindOrderItemByIdImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindOrderItemByIdTest {

    @Mock
    private OrderItemPersistence orderItemPersistence;

    @InjectMocks
    private FindOrderItemByIdImpl findOrderItemByIdImpl;

    private UUID validId;
    private UUID invalidId;
    private OrderItemDomain orderItemDomain;

    @BeforeEach
    void setUp() {
        validId = UUID.randomUUID();
        invalidId = UUID.randomUUID();
        orderItemDomain = new OrderItemDomain();
        orderItemDomain.setId(validId);
    }

    @Test
    void testExecute_shouldReturnOrderItem_whenIdIsValid() {
        when(orderItemPersistence.findById(validId)).thenReturn(Optional.of(orderItemDomain));

        OrderItemDomain result = (OrderItemDomain) findOrderItemByIdImpl.execute(validId);

        assertEquals(orderItemDomain, result);
    }

    @Test
    void testExecute_shouldThrowOrderNotFound_whenIdIsInvalid() {
        when(orderItemPersistence.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFound.class, () -> findOrderItemByIdImpl.execute(invalidId));
    }

}