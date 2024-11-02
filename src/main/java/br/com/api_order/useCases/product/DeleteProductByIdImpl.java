package br.com.api_order.useCases.product;

import br.com.api_order.domain.persistence.product.ProductPersistence;
import br.com.api_order.domain.useCases.product.DeleteProductById;
import br.com.api_order.domain.useCases.product.FindProductById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProductByIdImpl implements DeleteProductById {

    private final FindProductById findProductById;
    private final ProductPersistence productPersistence;

    @Override
    public void execute(UUID id) {
        findProductById.execute(id);
        productPersistence.deleteByID(id);
    }
}
