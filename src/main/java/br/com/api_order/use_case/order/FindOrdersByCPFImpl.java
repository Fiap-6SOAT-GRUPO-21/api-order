package br.com.api_order.use_case.order;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.use_case.customer.FindCustomerByCPF;
import br.com.api_order.domain.use_case.order.FindOrdersByCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindOrdersByCPFImpl implements FindOrdersByCPF {

    private final OrderPersistence orderPersistence;
    private final FindCustomerByCPF findCustomerByCPF;

    @Override
    public List<OrderDomain> execute(String cpf) {
        CustomerDTO customerDomain = findCustomerByCPF.execute(cpf);
        return orderPersistence.findByIdCustomer(customerDomain.getId());
    }
}
