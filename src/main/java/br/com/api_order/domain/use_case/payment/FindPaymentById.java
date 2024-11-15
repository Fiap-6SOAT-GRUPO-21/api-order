package br.com.api_order.domain.use_case.payment;

import br.com.api_order.application.dtos.payment.PaymentDTO;

import java.util.UUID;

public interface FindPaymentById {

    PaymentDTO execute(UUID idPayment);
}
