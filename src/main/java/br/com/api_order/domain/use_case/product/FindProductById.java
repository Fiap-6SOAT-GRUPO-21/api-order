package br.com.api_order.domain.use_case.product;

import br.com.api_order.application.dtos.product.ProductDTO;

import java.util.UUID;

public interface FindProductById {

    ProductDTO execute(UUID id);
}
