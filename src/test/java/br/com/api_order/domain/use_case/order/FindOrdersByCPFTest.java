package br.com.api_order.domain.use_case.order;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.use_case.customer.FindCustomerByCPF;
import br.com.api_order.use_case.order.FindOrdersByCPFImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindOrdersByCPFTest {

    @Mock
    private OrderPersistence orderPersistence;

    @Mock
    private FindCustomerByCPF findCustomerByCPF;

    @InjectMocks
    private FindOrdersByCPFImpl findOrdersByCPFImpl;

    @Test
    void shouldReturnOrdersWhenCustomerExists() {
        // Arrange
        String cpf = "12345678900";
        CustomerDTO mockCustomer = new CustomerDTO();
        mockCustomer.setId(UUID.fromString("ee983ecd-d16b-4c24-bd09-876d9e83245f"));
        mockCustomer.setName("Test Customer");
        when(findCustomerByCPF.execute(cpf)).thenReturn(mockCustomer);

        OrderDomain order1 = new OrderDomain();
        OrderDomain order2 = new OrderDomain();
        when(orderPersistence.findByIdCustomer(mockCustomer.getId()))
                .thenReturn(Arrays.asList(order1, order2));

        // Act
        List<OrderDomain> result = findOrdersByCPFImpl.execute(cpf);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(findCustomerByCPF, times(1)).execute(cpf);
        verify(orderPersistence, times(1)).findByIdCustomer(mockCustomer.getId());
    }

    @Test
    void shouldReturnEmptyListWhenNoOrdersForCustomer() {
        // Arrange
        String cpf = "12345678900";
        CustomerDTO mockCustomer = new CustomerDTO();
        mockCustomer.setId(UUID.fromString("ee983ecd-d16b-4c24-bd09-876d9e83245f"));
        mockCustomer.setName("Test Customer");
        when(findCustomerByCPF.execute(cpf)).thenReturn(mockCustomer);

        when(orderPersistence.findByIdCustomer(mockCustomer.getId()))
                .thenReturn(List.of());

        // Act
        List<OrderDomain> result = findOrdersByCPFImpl.execute(cpf);

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
        verify(findCustomerByCPF, times(1)).execute(cpf);
        verify(orderPersistence, times(1)).findByIdCustomer(mockCustomer.getId());
    }

    @Test
    void shouldThrowExceptionWhenCustomerNotFound() {
        // Arrange
        String cpf = "12345678900";
        when(findCustomerByCPF.execute(cpf)).thenThrow(new RuntimeException("Customer not found"));

        // Act & Assert
        RuntimeException exception =
                assertThrows(RuntimeException.class, () -> findOrdersByCPFImpl.execute(cpf));

        assertEquals("Customer not found", exception.getMessage());
        verify(findCustomerByCPF, times(1)).execute(cpf);
        verify(orderPersistence, never()).findByIdCustomer(any());
    }

}