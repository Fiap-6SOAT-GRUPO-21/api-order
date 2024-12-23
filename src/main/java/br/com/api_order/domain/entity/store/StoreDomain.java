package br.com.api_order.domain.entity.store;

import br.com.api_order.domain.entity.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDomain extends DomainEntity {
    private UUID id;

    private String name;

    private boolean active = true;

}
