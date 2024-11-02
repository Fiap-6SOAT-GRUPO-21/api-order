package br.com.api_order.useCases.product;

import br.com.api_order.domain.entity.product.ProductDomain;
import br.com.api_order.domain.persistence.product.ProductPersistence;
import br.com.api_order.domain.useCases.category.FindCategoryById;
import br.com.api_order.domain.useCases.product.FindAllByCategoryId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindAllByCategoryIdImpl implements FindAllByCategoryId {

    private final FindCategoryById findCategoryById;
    private final ProductPersistence productPersistence;

    @Override
    public List<ProductDomain> execute(UUID idCategory) {
        findCategoryById.execute(idCategory);

        return productPersistence.findAllByCategory(idCategory);
    }
}
