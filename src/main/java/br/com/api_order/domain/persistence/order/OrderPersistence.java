package br.com.api_order.domain.persistence.order;

import br.com.api_order.domain.entity.order.OrderDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderPersistence {

    Optional<OrderDomain> findById(UUID id);

    OrderDomain save(OrderDomain orderDomain);

    List<OrderDomain> findAll();

    List<OrderDomain> findAllOrdered();

    List<OrderDomain> findByIdCustomer(UUID idCustomer);
}
