package br.com.api_order.useCases.order;

import br.com.api_order.domain.entity.customer.CustomerDomain;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.enums.StatusOrder;
import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.entity.payment.PaymentDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.domain.entity.product.ProductDomain;
import br.com.api_order.domain.entity.store.StoreDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.useCases.customer.FindCustomerByCPF;
import br.com.api_order.domain.useCases.order.item.CreateNewOrderItem;
import br.com.api_order.domain.useCases.payment.MakeANewPayment;
import br.com.api_order.domain.useCases.payment.ProcessPayment;
import br.com.api_order.domain.useCases.product.FindProductById;
import br.com.api_order.domain.useCases.product.FindProductByIdAndIdStore;
import br.com.api_order.domain.useCases.store.FindStoreById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateNewOrderImplTest {

    @Mock
    private OrderPersistence orderPersistence;

    @Mock
    private CreateNewOrderItem createNewOrderItem;

    @Mock
    private FindStoreById findStoreById;

    @Mock
    private FindProductById findProductById;

    @Mock
    private FindProductByIdAndIdStore findProductByIdAndIdStore;

    @Mock
    private MakeANewPayment makeANewPayment;

    @Mock
    private FindCustomerByCPF findCustomerByCPF;

    @Mock
    private ProcessPayment processPayment;

    @InjectMocks
    private CreateNewOrderImpl createNewOrder;

    private OrderDomain orderDomain;
    private CustomerDomain customerDomain;
    private PaymentDomain paymentDomain;
    private Map<String, ProcessPayment> processPaymentMap;

    @BeforeEach
    void setUp() {
        orderDomain = new OrderDomain();
        OrderItemDomain o = new OrderItemDomain();
        o.setQuantity(1);
        o.setIdProduct(UUID.randomUUID());
        orderDomain.setItems(Collections.singletonList(o));

        customerDomain = new CustomerDomain();
        customerDomain.setId(UUID.randomUUID());

        paymentDomain = new PaymentDomain();
        paymentDomain.setId(UUID.randomUUID());

        processPaymentMap = new HashMap<>();
        processPaymentMap.put(PaymentType.MERCADO_PAGO.name(), processPayment);
        createNewOrder = new CreateNewOrderImpl(
                orderPersistence, createNewOrderItem, findStoreById, findProductById,
                findProductByIdAndIdStore, makeANewPayment, findCustomerByCPF, processPaymentMap
        );
    }

    @Test
    void testExecute() {
        // Configurando mocks para os métodos
        when(findCustomerByCPF.execute("123456789")).thenReturn(customerDomain);
        when(orderPersistence.save(any(OrderDomain.class))).thenReturn(orderDomain);
        StoreDomain t = new StoreDomain();
        t.setActive(true);
        when(findStoreById.execute(any())).thenReturn(t);
        ProductDomain t1 = new ProductDomain();
        t1.setPrice(new BigDecimal(10));
        when(findProductById.execute(any())).thenReturn(t1);
        when(createNewOrderItem.execute(any(OrderItemDomain.class))).thenAnswer(i -> i.getArgument(0));
        when(makeANewPayment.execute(any(OrderDomain.class), any(PaymentType.class), any(ProcessPayment.class)))
                .thenReturn(paymentDomain);

        // Executando o método
        OrderDomain result = createNewOrder.execute(orderDomain, "123456789", PaymentType.MERCADO_PAGO);

        // Verificações
        assertEquals(StatusOrder.RECEIVED, result.getStatus());
    }
}
