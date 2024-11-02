package br.com.api_order.useCases.store.payment;

import br.com.api_order.useCases.store.payment.exceptions.MercadoPagoGatewayNotFound;
import br.com.api_order.domain.entity.DomainEntity;
import br.com.api_order.domain.persistence.store.payment.MercadoPagoGatewayPersistence;
import br.com.api_order.domain.useCases.store.payment.FindMercadoPagoGatewayById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindMercadoPagoGatewayByIdImpl implements FindMercadoPagoGatewayById {

    private final MercadoPagoGatewayPersistence mercadoPagoGatewayPersistence;

    @Override
    public DomainEntity execute(UUID id) {
        return mercadoPagoGatewayPersistence.findById(id)
                .orElseThrow(MercadoPagoGatewayNotFound::new);
    }
}
