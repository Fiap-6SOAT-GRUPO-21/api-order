package br.com.api_order.domain.useCases.product;

import br.com.api_order.domain.entity.product.ProductDomain;

import java.util.List;

public interface FindAllProducts {

    List<ProductDomain> execute();
}
