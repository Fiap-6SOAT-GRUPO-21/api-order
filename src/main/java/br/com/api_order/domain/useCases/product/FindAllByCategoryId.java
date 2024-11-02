package br.com.api_order.domain.useCases.product;

import br.com.api_order.domain.entity.product.ProductDomain;

import java.util.List;
import java.util.UUID;

public interface FindAllByCategoryId {

    List<ProductDomain> execute(UUID idCategory);
}
