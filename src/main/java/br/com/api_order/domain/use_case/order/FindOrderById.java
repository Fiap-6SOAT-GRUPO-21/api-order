package br.com.api_order.domain.use_case.order;

import br.com.api_order.domain.entity.order.OrderDomain;

import java.util.UUID;

public interface FindOrderById {

    OrderDomain execute(UUID id);
}
