package br.com.api_order.useCases.manager;

import br.com.api_order.domain.entity.manager.ManagerDomain;
import br.com.api_order.domain.useCases.manager.CreateNewManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateNewManagerImpl implements CreateNewManager {

    @Override
    public ManagerDomain execute(ManagerDomain managerDomain) {
        return null;
    }
}
