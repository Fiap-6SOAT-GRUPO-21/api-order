package br.com.api_order.domain.use_case.store;

import br.com.api_order.application.dtos.store.StoreDTO;

import java.util.UUID;

public interface FindStoreById {

    StoreDTO execute(UUID id);

}
