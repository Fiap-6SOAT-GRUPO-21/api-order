package br.com.api_order.domain.persistence.payment;

import br.com.api_order.domain.entity.payment.PaymentDomain;

import java.util.Optional;
import java.util.UUID;

public interface PaymentPersistence {

    PaymentDomain save(PaymentDomain paymentDomain);

    void deleteByID(UUID id);

    Optional<PaymentDomain> findById(UUID idPayment);
}
