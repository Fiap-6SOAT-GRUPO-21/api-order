package br.com.api_order.useCases.category;

import br.com.api_order.domain.persistence.category.CategoryPersistence;
import br.com.api_order.domain.useCases.store.FindStoreById;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CreateNewCategoryImplTest {

    @Mock
    private FindStoreById findStoreById;

    @Mock
    private CategoryPersistence categoryPersistence;

    @InjectMocks
    private CreateNewCategoryImpl useCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void contextLoads() {
        assert(true);
    }

}