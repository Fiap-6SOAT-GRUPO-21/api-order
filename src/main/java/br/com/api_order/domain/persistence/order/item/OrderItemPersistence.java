package br.com.api_order.domain.persistence.order.item;

import br.com.api_order.domain.entity.order.item.OrderItemDomain;

import java.util.Optional;
import java.util.UUID;

public interface OrderItemPersistence {

    Optional<OrderItemDomain> findById(UUID id);

    OrderItemDomain save(OrderItemDomain orderItemDomain);
}
