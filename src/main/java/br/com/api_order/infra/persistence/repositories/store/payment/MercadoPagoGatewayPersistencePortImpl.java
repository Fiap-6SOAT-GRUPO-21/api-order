package br.com.api_order.infra.persistence.repositories.store.payment;


import br.com.api_order.domain.entity.store.payment.MercadoPagoGatewayDomain;
import br.com.api_order.domain.persistence.store.payment.MercadoPagoGatewayPersistence;
import br.com.api_order.infra.persistence.entities.store.payment.MercadoPagoGatewayEntity;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class MercadoPagoGatewayPersistencePortImpl implements MercadoPagoGatewayPersistence {

    private final MercadoPagoGatewayJpaRepository mercadoPagoGatewayJpaRepository;
    private final ModelMapper modelMapper;


    @Override
    public MercadoPagoGatewayDomain save(MercadoPagoGatewayDomain mercadoPagoGatewayDomain) {
        MercadoPagoGatewayEntity map = modelMapper.map(mercadoPagoGatewayDomain, MercadoPagoGatewayEntity.class);
        MercadoPagoGatewayEntity mercadoPagoGateway = mercadoPagoGatewayJpaRepository.save(map);
        return modelMapper.map(mercadoPagoGateway, MercadoPagoGatewayDomain.class);
    }

    @Override
    public Optional<MercadoPagoGatewayDomain> findById(UUID id) {
        return mercadoPagoGatewayJpaRepository.findById(id)
                .map(storeEntity -> modelMapper.map(storeEntity, MercadoPagoGatewayDomain.class));
    }
}
