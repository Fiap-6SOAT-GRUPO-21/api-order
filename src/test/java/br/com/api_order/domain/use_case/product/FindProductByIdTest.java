package br.com.api_order.domain.use_case.product;

import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import br.com.api_order.use_case.product.FindProductByIdImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductByIdTest {

    @Mock
    private ApiFood apiFood;

    @InjectMocks
    private FindProductByIdImpl findProductById;

    @Test
    void testExecute() {
        UUID idProduct = UUID.randomUUID();
        ProductDTO expectedProduct = new ProductDTO();
        expectedProduct.setId(idProduct);

        when(apiFood.findProductByIdAndIdStore(idProduct.toString())).thenReturn(expectedProduct);

        ProductDTO actualProduct = findProductById.execute(idProduct);

        assertEquals(expectedProduct, actualProduct);
    }

}