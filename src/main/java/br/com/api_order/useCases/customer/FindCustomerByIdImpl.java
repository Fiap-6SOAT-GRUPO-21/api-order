package br.com.api_order.useCases.customer;

import br.com.api_order.domain.entity.customer.CustomerDomain;
import br.com.api_order.useCases.customer.exceptions.CustomerNotFound;
import br.com.api_order.domain.persistence.customer.CustomerPersistence;
import br.com.api_order.domain.useCases.customer.FindCustomerById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindCustomerByIdImpl implements FindCustomerById {

    private final CustomerPersistence customerPersistencePort;
    @Override
    public CustomerDomain execute(UUID id) {
        return customerPersistencePort.findById(id)
                .orElseThrow(CustomerNotFound::new);
    }
}
