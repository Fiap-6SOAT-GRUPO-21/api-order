package br.com.api_order.domain.use_case.order.item;

import br.com.api_order.domain.entity.DomainEntity;

import java.util.UUID;

public interface FindOrderItemById {

    DomainEntity execute(UUID id);
}
