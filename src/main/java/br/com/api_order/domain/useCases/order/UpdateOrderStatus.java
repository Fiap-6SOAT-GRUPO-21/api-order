package br.com.api_order.domain.useCases.order;

import br.com.api_order.domain.entity.order.enums.StatusOrder;

import java.util.UUID;

public interface UpdateOrderStatus {

    void execute(UUID id, StatusOrder status);
}
