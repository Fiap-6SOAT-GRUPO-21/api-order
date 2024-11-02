package br.com.api_order.domain.useCases.store.payment;

import br.com.api_order.domain.entity.DomainEntity;
import br.com.api_order.domain.entity.store.payment.MercadoPagoGatewayDomain;

public interface CreateNewMercadoPagoGateway {

    DomainEntity execute(MercadoPagoGatewayDomain mercadoPagoGatewayDomain);
}
