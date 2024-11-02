package br.com.api_order.domain.useCases.customer;

import br.com.api_order.domain.entity.customer.CustomerDomain;

import java.util.UUID;

public interface FindCustomerById {

    CustomerDomain execute(UUID id);
}
