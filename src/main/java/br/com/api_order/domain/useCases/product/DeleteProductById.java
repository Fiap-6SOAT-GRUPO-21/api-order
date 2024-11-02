package br.com.api_order.domain.useCases.product;

import java.util.UUID;

public interface DeleteProductById {

    void execute(UUID id);
}
