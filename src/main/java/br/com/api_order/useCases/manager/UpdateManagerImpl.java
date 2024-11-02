package br.com.api_order.useCases.manager;

import br.com.api_order.domain.entity.manager.ManagerDomain;
import br.com.api_order.domain.useCases.manager.UpdateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateManagerImpl implements UpdateManager {
    @Override
    public ManagerDomain execute(ManagerDomain updateMangerDomain) {
        return null;
    }
}
