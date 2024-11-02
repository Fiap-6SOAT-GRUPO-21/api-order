package br.com.api_order.domain.useCases.product;

import br.com.api_order.domain.entity.product.ProductDomain;

import java.util.UUID;

public interface FindProductByIdAndIdStore {

    ProductDomain execute(UUID idProduct, UUID idStore);
}
