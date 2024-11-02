package br.com.api_order.domain.useCases.category;

import br.com.api_order.domain.entity.category.CategoryDomain;

public interface UpdateCategory {

    CategoryDomain execute(CategoryDomain updateCategoryDomain);
}
