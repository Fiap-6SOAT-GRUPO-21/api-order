package br.com.api_order.domain.persistence.customer;

import br.com.api_order.domain.entity.customer.CustomerDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerPersistence {

    Optional<CustomerDomain> findById(UUID id);

    CustomerDomain save(CustomerDomain customerDomain);

    Optional<CustomerDomain> findByCpf(String cpf);

    void deleteByID(UUID id);

    List<CustomerDomain> findAll();
}
