package br.com.api_order.domain.useCases.category;

import br.com.api_order.domain.entity.category.CategoryDomain;

import java.util.List;

public interface FindAllCategories {

    List<CategoryDomain> execute();
}
