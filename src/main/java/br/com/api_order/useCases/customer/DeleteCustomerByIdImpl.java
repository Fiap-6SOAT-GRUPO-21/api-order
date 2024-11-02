package br.com.api_order.useCases.customer;

import br.com.api_order.domain.persistence.customer.CustomerPersistence;
import br.com.api_order.domain.useCases.customer.DeleteCustomerById;
import br.com.api_order.domain.useCases.customer.FindCustomerById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCustomerByIdImpl implements DeleteCustomerById {

    private final CustomerPersistence customerPersistence;
    private final FindCustomerById findCustomerById;

    @Override
    public void execute(UUID id) {
        findCustomerById.execute(id);
        customerPersistence.deleteByID(id);
    }
}
