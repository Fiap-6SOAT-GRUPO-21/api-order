package br.com.api_order.domain.useCases.product;

import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.domain.entity.product.ProductDomain;

import java.util.UUID;

public interface FindProductById {

    ProductDTO execute(UUID id);
}
