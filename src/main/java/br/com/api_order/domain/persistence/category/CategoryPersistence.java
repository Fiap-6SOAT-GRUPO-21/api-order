package br.com.api_order.domain.persistence.category;

import br.com.api_order.domain.entity.category.CategoryDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryPersistence {

    Optional<CategoryDomain> findById(UUID id);

    CategoryDomain save(CategoryDomain categoryDomain);

    void deleteByID(UUID id);

    List<CategoryDomain> findAll();
}
