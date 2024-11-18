package br.com.api_order.domain.use_case.payment;

import br.com.api_order.application.dtos.payment.PaymentIntegrationOrder;
import br.com.api_order.application.dtos.payment.PaymentIntegrationResult;

public interface ProcessPayment {

    PaymentIntegrationResult processPayment(PaymentIntegrationOrder paymentIntegrationOrder);

}
