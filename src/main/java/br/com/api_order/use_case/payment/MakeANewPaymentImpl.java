package br.com.api_order.use_case.payment;

import br.com.api_order.application.dtos.payment.*;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentStatus;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.domain.use_case.payment.MakeANewPayment;
import br.com.api_order.domain.use_case.payment.ProcessPayment;
import br.com.api_order.infra.gateways.internal.payments.ApiPayments;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class MakeANewPaymentImpl implements MakeANewPayment {

    final ApiPayments apiPayments;

    @Override
    public PaymentDTO execute(OrderDomain orderDomain, PaymentType provider, ProcessPayment processPayment) {

        List<PaymentIntegrationItem> item = new ArrayList<>();

        orderDomain.getItems().forEach(itemDomain -> item.add(new PaymentIntegrationItem(
                itemDomain.getQuantity(),
                itemDomain.getProduct().getPrice().multiply(BigDecimal.valueOf(itemDomain.getQuantity())),
                itemDomain.getProduct().getPrice(),
                itemDomain.getProduct().getName()
        )));


        PaymentIntegrationOrder paymentIntegrationOrder = new PaymentIntegrationOrder(
                orderDomain.getIdStore(),
                UUID.randomUUID(),
                orderDomain.getTotal(),
                item
        );

        PaymentIntegrationResult paymentIntegrationResult = processPayment.processPayment(paymentIntegrationOrder);

        PaymentRequestDTO paymentRequest = PaymentRequestDTO.builder()
                .amount(orderDomain.getTotal())
                .type(provider)
                .qrCode(paymentIntegrationResult.getQrCode())
                .status(PaymentStatus.PENDING)
                .build();

        return apiPayments.createPayment(paymentRequest);
    }
}
