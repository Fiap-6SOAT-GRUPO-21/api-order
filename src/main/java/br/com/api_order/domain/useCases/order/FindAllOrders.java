package br.com.api_order.domain.useCases.order;

import br.com.api_order.domain.entity.order.OrderDomain;

import java.util.List;

public interface FindAllOrders {

    List<OrderDomain> execute();
}
