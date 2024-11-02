package br.com.api_order.domain.useCases.manager;

import java.util.UUID;

public interface DeleteManagerById {

    void execute(UUID id);
}
