package br.com.api_order.use_case.product;

import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.domain.use_case.product.FindProductById;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindProductByIdImpl implements FindProductById {

    final ApiFood apiFood;

    @Override
    public ProductDTO execute(UUID id) {
        return apiFood.findProductByIdAndIdStore(id);
    }

}
