package br.com.api_order.useCases.store;

import br.com.api_order.domain.entity.store.StoreDomain;
import br.com.api_order.domain.persistence.store.StorePersistence;
import br.com.api_order.domain.useCases.store.FindAllStores;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllStoresImpl implements FindAllStores {

    final StorePersistence storePersistence;

    @Override
    public List<StoreDomain> execute() {
        return storePersistence.findAll();
    }
}
