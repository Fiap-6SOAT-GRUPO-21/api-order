package br.com.api_order.useCases.product;

import br.com.api_order.useCases.product.exceptions.ProductNotFound;
import br.com.api_order.domain.entity.product.ProductDomain;
import br.com.api_order.domain.persistence.product.ProductPersistence;
import br.com.api_order.domain.useCases.product.FindProductById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProductByIdImpl implements FindProductById {

    private final ProductPersistence productPersistence;
    @Override
    public ProductDomain execute(UUID id) {
        return productPersistence.findById(id)
                .orElseThrow(ProductNotFound::new);
    }
}
