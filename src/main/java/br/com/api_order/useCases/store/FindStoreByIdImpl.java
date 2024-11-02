package br.com.api_order.useCases.store;

import br.com.api_order.domain.entity.store.StoreDomain;
import br.com.api_order.domain.persistence.store.StorePersistence;
import br.com.api_order.domain.useCases.store.FindStoreById;
import br.com.api_order.useCases.store.exceptions.StoreNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindStoreByIdImpl implements FindStoreById {

    final StorePersistence storePersistence;
    @Override
    public StoreDomain execute(UUID id) {
        return storePersistence.findById(id)
                .orElseThrow(StoreNotFound::new);
    }
}
