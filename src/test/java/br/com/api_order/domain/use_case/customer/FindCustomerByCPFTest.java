package br.com.api_order.domain.use_case.customer;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import br.com.api_order.use_case.customer.FindCustomerByCPFImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindCustomerByCPFTest {

    @Mock
    private ApiFood apiFood;

    @InjectMocks
    private FindCustomerByCPFImpl findCustomerByCPF;

    @Test
    void testExecute_shouldReturnCustomerDTO_whenGivenValidCpf() {
        String cpf = "12345678900";
        CustomerDTO mockCustomer = CustomerDTO.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .cpf(cpf)
                .idStore("22391074-e824-434d-b37b-4b152e09f49a")
                .build();

        when(apiFood.findCustomerByCpf(cpf)).thenReturn(mockCustomer);

        CustomerDTO result = findCustomerByCPF.execute(cpf);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals(cpf, result.getCpf());
        assertEquals(mockCustomer.getIdStore(), result.getIdStore());
    }

}