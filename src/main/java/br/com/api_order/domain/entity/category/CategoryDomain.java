package br.com.api_order.domain.entity.category;

import br.com.api_order.domain.entity.DomainEntity;
import br.com.api_order.domain.entity.store.StoreDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDomain extends DomainEntity {
    private String name;
    private UUID idStore;
    private StoreDomain store;
}
