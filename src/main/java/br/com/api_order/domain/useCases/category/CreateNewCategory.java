package br.com.api_order.domain.useCases.category;

import br.com.api_order.domain.entity.category.CategoryDomain;

public interface CreateNewCategory {

   CategoryDomain execute(CategoryDomain categoryDomain);
}
