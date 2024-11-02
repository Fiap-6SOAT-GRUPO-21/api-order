package br.com.api_order.domain.entity.product;

import br.com.api_order.domain.entity.DomainEntity;
import br.com.api_order.domain.entity.category.CategoryDomain;
import br.com.api_order.domain.entity.store.StoreDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDomain extends DomainEntity {
    private UUID id;
    private String name;
    private BigDecimal price;
    private UUID idStore;
    private StoreDomain store;
    private UUID idCategory;
    private CategoryDomain category;
}
