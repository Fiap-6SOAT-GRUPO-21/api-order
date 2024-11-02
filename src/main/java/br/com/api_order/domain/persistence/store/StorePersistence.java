package br.com.api_order.domain.persistence.store;

import br.com.api_order.domain.entity.store.StoreDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StorePersistence {

    Optional<StoreDomain> findById(UUID id);

    StoreDomain save(StoreDomain storeDomain);

    List<StoreDomain> findAll();
}
