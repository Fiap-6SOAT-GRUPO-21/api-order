package br.com.api_order.domain.useCases.category;

import br.com.api_order.domain.entity.category.CategoryDomain;

import java.util.UUID;

public interface FindCategoryById {

    CategoryDomain execute(UUID id);
}
