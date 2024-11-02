package br.com.api_order.domain.useCases.manager;

import br.com.api_order.domain.entity.manager.ManagerDomain;

public interface UpdateManager {

    ManagerDomain execute(ManagerDomain updateMangerDomain);
}
