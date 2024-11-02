package br.com.api_order.useCases.product;

import br.com.api_order.useCases.product.exceptions.ProductInvalidPrice;
import br.com.api_order.domain.entity.product.ProductDomain;
import br.com.api_order.domain.persistence.product.ProductPersistence;
import br.com.api_order.domain.useCases.category.FindCategoryById;
import br.com.api_order.domain.useCases.product.CreateNewProduct;
import br.com.api_order.domain.useCases.store.FindStoreById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CreateNewProductImpl implements CreateNewProduct {

    private final FindCategoryById findCategoryById;
    private final FindStoreById findStoreById;
    private final ProductPersistence productPersistence;

    @Override
    public ProductDomain execute(ProductDomain productDomain) {

        findCategoryById.execute(productDomain.getIdCategory());
        findStoreById.execute(productDomain.getIdStore());

        if (productDomain.getPrice().compareTo(BigDecimal.ZERO) <= 0)
            throw new ProductInvalidPrice();


        return productPersistence.save(productDomain);
    }
}
