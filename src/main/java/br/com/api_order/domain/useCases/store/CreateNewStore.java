package br.com.api_order.domain.useCases.store;

import br.com.api_order.domain.entity.store.StoreDomain;

public interface CreateNewStore {

   StoreDomain execute(StoreDomain storeDomain);
}
