package br.com.api_order.domain.useCases.store;

import br.com.api_order.domain.entity.store.StoreDomain;

import java.util.UUID;

public interface FindStoreById {

    StoreDomain execute(UUID id);
}
