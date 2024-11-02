package br.com.api_order.domain.useCases.customer;

import br.com.api_order.domain.entity.customer.CustomerDomain;

public interface FindCustomerByCPF {

    CustomerDomain execute(String cpf);
}
