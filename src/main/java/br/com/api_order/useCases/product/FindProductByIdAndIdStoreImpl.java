package br.com.api_order.useCases.product;

import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.domain.useCases.product.FindProductByIdAndIdStore;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProductByIdAndIdStoreImpl implements FindProductByIdAndIdStore {

    private final ApiFood apiFood;

    @Override
    public ProductDTO execute(UUID idProduct, UUID idStore) {
        return apiFood.findProductByIdAndIdStore(idProduct.toString());
    }
}
