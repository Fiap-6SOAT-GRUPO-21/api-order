package br.com.api_order.domain.use_case.product;

import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import br.com.api_order.use_case.product.FindProductByIdAndIdStoreImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindProductByIdAndIdStoreTest {

    @Mock
    private ApiFood apiFood;

    @InjectMocks
    private FindProductByIdAndIdStoreImpl findProductByIdAndIdStore;
    
    @Test
    void testExecute() {
        UUID idProduct = UUID.randomUUID();
        UUID idStore = UUID.randomUUID();
        ProductDTO expectedProduct = new ProductDTO();
        expectedProduct.setId(idProduct);
        expectedProduct.setIdStore(idStore.toString());

        when(apiFood.findProductByIdAndIdStore(idProduct)).thenReturn(expectedProduct);

        ProductDTO actualProduct = findProductByIdAndIdStore.execute(idProduct, idStore);

        assertEquals(expectedProduct, actualProduct);
    }

}