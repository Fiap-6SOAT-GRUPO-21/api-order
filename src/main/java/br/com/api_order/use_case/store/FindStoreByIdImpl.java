package br.com.api_order.use_case.store;

import br.com.api_order.application.dtos.store.StoreDTO;
import br.com.api_order.domain.use_case.store.FindStoreById;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindStoreByIdImpl implements FindStoreById {

    final ApiFood apiFood;

    @Override
    public StoreDTO execute(UUID id) {
        return apiFood.getStoreById(id);
    }
}
