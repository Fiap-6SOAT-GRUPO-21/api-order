package br.com.api_order.useCases.customer;

import br.com.api_order.domain.entity.customer.CustomerDomain;
import br.com.api_order.domain.persistence.customer.CustomerPersistence;
import br.com.api_order.domain.useCases.customer.FindAllCustomers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllCustomersImpl implements FindAllCustomers {

    private final CustomerPersistence customerPersistencePort;
    @Override
    public List<CustomerDomain> execute() {
        return customerPersistencePort.findAll();
    }
}
