package br.com.api_order.domain.useCases.customer;

import java.util.UUID;

public interface DeleteCustomerById {

    void execute(UUID id);
}
