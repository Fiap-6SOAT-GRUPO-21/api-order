package br.com.api_order.domain.useCases.payment;

import br.com.api_order.domain.entity.payment.PaymentDomain;

import java.util.UUID;

public interface FindPaymentById {

    PaymentDomain execute(UUID idPayment);
}
