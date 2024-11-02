package br.com.api_order.domain.entity.store.payment;


import br.com.api_order.domain.entity.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MercadoPagoGatewayDomain extends DomainEntity {

    private String collectors;
    private String externalPos;
}
