package br.com.api_order.domain.useCases.customer;

import br.com.api_order.application.dtos.customer.CustomerDTO;

import java.util.UUID;

public interface FindCustomerById {

    CustomerDTO execute(UUID id);
}
