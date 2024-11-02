package br.com.api_order.domain.useCases.manager;

import br.com.api_order.domain.entity.manager.ManagerDomain;

public interface FindManagerByCPF {

    ManagerDomain execute(String cpf);
}
