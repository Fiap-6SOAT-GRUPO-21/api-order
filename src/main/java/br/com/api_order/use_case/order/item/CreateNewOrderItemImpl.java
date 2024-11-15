package br.com.api_order.use_case.order.item;

import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.persistence.order.item.OrderItemPersistence;
import br.com.api_order.domain.use_case.order.item.CreateNewOrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewOrderItemImpl implements CreateNewOrderItem {

    final OrderItemPersistence orderItemPersistence;
    @Override
    public OrderItemDomain execute(OrderItemDomain orderItemDomain) {
        return orderItemPersistence.save(orderItemDomain);
    }
}
