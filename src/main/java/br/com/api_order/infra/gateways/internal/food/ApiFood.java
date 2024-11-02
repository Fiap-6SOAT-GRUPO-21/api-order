package br.com.api_order.infra.gateways.internal.food;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.application.dtos.store.StoreDTO;
import br.com.api_order.infra.interceptor.DefaultInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "internal-api-food",
        url = "${api.url.api-food}",
        configuration = DefaultInterceptor.class
)
public interface ApiFood {

    @GetMapping("/store/{id}")
    StoreDTO getStoreById(@PathVariable("id")String id);

    @GetMapping("/product/{id}")
    ProductDTO findProductByIdAndIdStore(@PathVariable("id")String id);

    @GetMapping("/customer/{id}")
    CustomerDTO findCustomerByCpf(@PathVariable("id")String id);
}
