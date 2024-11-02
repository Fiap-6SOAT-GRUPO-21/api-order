package br.com.api_order.domain.useCases.product;

import br.com.api_order.domain.entity.product.ProductDomain;

public interface UpdateProduct {

    ProductDomain execute(ProductDomain updateProductDomain);
}
