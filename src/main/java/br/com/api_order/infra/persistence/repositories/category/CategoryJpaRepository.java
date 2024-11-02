package br.com.api_order.infra.persistence.repositories.category;

import br.com.api_order.infra.persistence.entities.category.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {

}
