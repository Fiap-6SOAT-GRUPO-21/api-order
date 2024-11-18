package br.com.api_order.domain.use_case.payment;

import br.com.api_order.application.dtos.payment.PaymentDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;

public interface MakeANewPayment {

    PaymentDTO execute(OrderDomain orderDomain, PaymentType provider);
}
