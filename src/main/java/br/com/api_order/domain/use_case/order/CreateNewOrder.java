package br.com.api_order.domain.use_case.order;

import br.com.api_order.application.dtos.order.response.OrderResponse;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;

public interface CreateNewOrder {

    OrderResponse execute(OrderDomain orderDomain, String cpf, PaymentType provider);
}
