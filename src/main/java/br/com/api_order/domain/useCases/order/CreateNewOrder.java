package br.com.api_order.domain.useCases.order;

import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.entity.payment.enums.PaymentType;

public interface CreateNewOrder {

   OrderDomain execute(OrderDomain orderDomain, String cpf, PaymentType provider);
}
