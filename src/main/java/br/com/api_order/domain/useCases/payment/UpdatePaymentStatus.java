package br.com.api_order.domain.useCases.payment;

public interface UpdatePaymentStatus {

    void execute(Long merchantOrderId);
}
