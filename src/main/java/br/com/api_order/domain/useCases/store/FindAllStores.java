package br.com.api_order.domain.useCases.store;

import br.com.api_order.domain.entity.store.StoreDomain;

import java.util.List;

public interface FindAllStores {

    List<StoreDomain> execute();
}
