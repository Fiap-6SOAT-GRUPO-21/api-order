package br.com.api_order.useCases.category;

import br.com.api_order.domain.entity.category.CategoryDomain;
import br.com.api_order.domain.persistence.category.CategoryPersistence;
import br.com.api_order.domain.useCases.category.CreateNewCategory;
import br.com.api_order.domain.useCases.store.FindStoreById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewCategoryImpl implements CreateNewCategory {

    private final FindStoreById findStoreById;
    private final CategoryPersistence categoryPersistence;

    @Override
    public CategoryDomain execute(CategoryDomain categoryDomain) {
        findStoreById.execute(categoryDomain.getIdStore());
        return categoryPersistence.save(categoryDomain);
    }
}
