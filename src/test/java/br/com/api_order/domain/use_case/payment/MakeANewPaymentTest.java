package br.com.api_order.domain.use_case.payment;

import br.com.api_order.application.dtos.payment.PaymentDTO;
import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentStatus;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.infra.gateways.internal.payments.ApiPayments;
import br.com.api_order.infra.gateways.internal.payments.dto.NewPaymentDTO;
import br.com.api_order.use_case.payment.MakeANewPaymentImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MakeANewPaymentTest {

    @Mock
    private ApiPayments apiPayments;

    @InjectMocks
    private MakeANewPaymentImpl makeANewPaymentImpl;

    private OrderDomain orderDomain;
    private PaymentType paymentType;
    private PaymentDTO paymentDTO;

    @BeforeEach
    void setUp() {
        orderDomain = new OrderDomain();
        orderDomain.setId(UUID.randomUUID());
        orderDomain.setTotal(BigDecimal.valueOf(100.00));
        orderDomain.setIdStore(UUID.randomUUID());

        OrderItemDomain item1 = new OrderItemDomain();
        item1.setIdProduct(UUID.randomUUID());
        item1.setIdOrder(orderDomain.getId());
        item1.setQuantity(1);

        ProductDTO productDomain = new ProductDTO();
        productDomain.setId(UUID.randomUUID());
        productDomain.setName("Product 1");
        productDomain.setPrice(BigDecimal.valueOf(50.00));
        productDomain.setIdStore(UUID.randomUUID().toString());

        item1.setProduct(productDomain);

        OrderItemDomain item2 = new OrderItemDomain();
        item2.setIdProduct(UUID.randomUUID());
        item2.setProduct(productDomain);
        item2.setIdOrder(orderDomain.getId());
        item2.setQuantity(1);

        orderDomain.setItems(Arrays.asList(item1, item2));

        paymentType = PaymentType.MERCADO_PAGO;

        paymentDTO = new PaymentDTO();
        paymentDTO.setId(UUID.randomUUID());
        paymentDTO.setStatus(PaymentStatus.PENDING);
    }

    @Test
    void testExecute_shouldCreateANewPayment_whenOrderDomainAndPaymentTypeAreValid() {
        when(apiPayments.createPayment(any(NewPaymentDTO.class))).thenReturn(paymentDTO);

        PaymentDTO result = makeANewPaymentImpl.execute(orderDomain, paymentType);

        assertEquals(paymentDTO, result);
    }

}