package br.com.api_order.useCases.manager;

import br.com.api_order.domain.entity.manager.ManagerDomain;
import br.com.api_order.domain.useCases.manager.FindManagerByCPF;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindManagerByCPFImpl implements FindManagerByCPF {

    @Override
    public ManagerDomain execute(String cpf) {
        return null;
    }
}
