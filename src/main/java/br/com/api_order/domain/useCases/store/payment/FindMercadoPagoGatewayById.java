package br.com.api_order.domain.useCases.store.payment;

import br.com.api_order.domain.entity.DomainEntity;

import java.util.UUID;

public interface FindMercadoPagoGatewayById {

    DomainEntity execute(UUID id);
}
