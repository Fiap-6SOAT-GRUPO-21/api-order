package br.com.api_order.domain.use_case.store;

import br.com.api_order.application.dtos.store.StoreDTO;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import br.com.api_order.use_case.store.FindStoreByIdImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindStoreByIdTest {

    @Mock
    private ApiFood apiFood;

    @InjectMocks
    private FindStoreByIdImpl findStoreById;

    @Test
    void testFindStoreById_shouldReturnStoreDTO_whenStoreExists() {
        final UUID storeId = UUID.fromString("22391074-e824-434d-b37b-4b152e09f49a");
        StoreDTO mockStore = new StoreDTO();
        mockStore.setId(storeId);
        mockStore.setName("Test Store");
        mockStore.setActive(true);

        when(apiFood.getStoreById(storeId.toString())).thenReturn(mockStore);

        StoreDTO result = findStoreById.execute(storeId);

        assertNotNull(result);
        assertEquals("Test Store", result.getName());
        assertEquals(storeId, result.getId());
    }

}
