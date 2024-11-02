package br.com.api_order.infra.persistence.repositories.store.payment;

import br.com.api_order.infra.persistence.entities.store.payment.MercadoPagoGatewayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MercadoPagoGatewayJpaRepository extends JpaRepository<MercadoPagoGatewayEntity, UUID> {
}
