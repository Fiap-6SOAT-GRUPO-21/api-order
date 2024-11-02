package br.com.api_order.useCases.order;

import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.useCases.order.FindAllOrdersOrdered;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllOrdersOrderedImpl implements FindAllOrdersOrdered {

    private final OrderPersistence orderPersistence;
    @Override
    public List<OrderDomain> execute() {
        return orderPersistence.findAllOrdered();
    }
}
