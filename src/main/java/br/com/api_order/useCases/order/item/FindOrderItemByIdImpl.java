package br.com.api_order.useCases.order.item;

import br.com.api_order.useCases.order.exceptions.OrderNotFound;
import br.com.api_order.domain.entity.DomainEntity;
import br.com.api_order.domain.persistence.order.item.OrderItemPersistence;
import br.com.api_order.domain.useCases.order.item.FindOrderItemById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindOrderItemByIdImpl implements FindOrderItemById {

    private final OrderItemPersistence orderItemPersistence;
    @Override
    public DomainEntity execute(UUID id) {
        return orderItemPersistence.findById(id)
                .orElseThrow(OrderNotFound::new);
    }
}
