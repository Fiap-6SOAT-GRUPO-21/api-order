package br.com.api_order.infra.persistence.repositories.order.item;


import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.persistence.order.item.OrderItemPersistence;
import br.com.api_order.infra.persistence.entities.order.item.OrderItemEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class OrderItemPersistencePortImpl implements OrderItemPersistence {

    private final OrderItemJpaRepository orderJpaRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<OrderItemDomain> findById(UUID id) {
        return orderJpaRepository.findById(id)
                .map(orderItemEntity -> modelMapper.map(orderItemEntity, OrderItemDomain.class));
    }

    @Override
    public OrderItemDomain save(OrderItemDomain orderDomain) {
        OrderItemEntity map = modelMapper.map(orderDomain, OrderItemEntity.class);
        OrderItemEntity orderItemEntity = orderJpaRepository.save(map);
        return modelMapper.map(orderItemEntity, OrderItemDomain.class);
    }

}
