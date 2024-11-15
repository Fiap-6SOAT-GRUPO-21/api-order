package br.com.api_order.domain.use_case.order.item;

import br.com.api_order.domain.entity.order.item.OrderItemDomain;

public interface CreateNewOrderItem {

    OrderItemDomain execute(OrderItemDomain orderItemDomain);
}
