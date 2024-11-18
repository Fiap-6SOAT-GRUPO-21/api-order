package br.com.api_order.infra.gateways.internal.food;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.application.dtos.store.StoreDTO;
import br.com.api_order.infra.interceptor.DefaultInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "internal-api-food",
        url = "${api.url.api-food}",
        configuration = DefaultInterceptor.class
)
public interface ApiFood {

    @GetMapping("/store/{id}")
    StoreDTO getStoreById(@PathVariable("id") UUID id);

    @GetMapping("/product/{id}")
    ProductDTO findProductByIdAndIdStore(@PathVariable("id") UUID id);

    @GetMapping("/customer/cpf/{cpf}")
    CustomerDTO findCustomerByCpf(@PathVariable("cpf") String cpf);
}
