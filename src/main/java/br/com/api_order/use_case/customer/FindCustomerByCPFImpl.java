package br.com.api_order.use_case.customer;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.domain.use_case.customer.FindCustomerByCPF;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindCustomerByCPFImpl implements FindCustomerByCPF {

    final ApiFood apiFood;

    @Override
    public CustomerDTO execute(String cpf) {
       return apiFood.findCustomerByCpf(cpf);
    }
}
