package br.com.api_order.domain.use_case.customer;

import br.com.api_order.application.dtos.customer.CustomerDTO;

public interface FindCustomerByCPF {

    CustomerDTO execute(String cpf);

}

