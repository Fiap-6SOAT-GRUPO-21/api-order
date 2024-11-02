package br.com.api_order.useCases.customer;

import br.com.api_order.useCases.customer.exceptions.CustomerNotFoundByCPF;
import br.com.api_order.domain.entity.customer.CustomerDomain;
import br.com.api_order.domain.persistence.customer.CustomerPersistence;
import br.com.api_order.domain.useCases.customer.FindCustomerByCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindCustomerByCPFImpl implements FindCustomerByCPF {

    private final CustomerPersistence customerPersistencePort;
    @Override
    public CustomerDomain execute(String cpf) {
        return customerPersistencePort.findByCpf(cpf)
                .orElseThrow(CustomerNotFoundByCPF::new);
    }
}
