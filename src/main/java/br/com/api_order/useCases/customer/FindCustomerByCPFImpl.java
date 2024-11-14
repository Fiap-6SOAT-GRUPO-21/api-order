package br.com.api_order.useCases.customer;

import br.com.api_order.application.dtos.customer.CustomerDTO;
import br.com.api_order.domain.useCases.customer.FindCustomerByCPF;
import br.com.api_order.infra.gateways.internal.food.ApiFood;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindCustomerByCPFImpl implements FindCustomerByCPF {

    final ApiFood apiFood;

    @Override
    public CustomerDTO execute(String cpf) {
        CustomerDTO customerResponse = apiFood.findCustomerByCpf(cpf);
        return CustomerDTO.builder()
                .name(customerResponse.getName())
                .email(customerResponse.getEmail())
                .cpf(customerResponse.getCpf())
                .idStore(customerResponse.getIdStore())
                .build();
    }
}
