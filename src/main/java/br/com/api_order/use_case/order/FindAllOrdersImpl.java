package br.com.api_order.use_case.order;

import br.com.api_order.domain.entity.order.OrderDomain;
import br.com.api_order.domain.persistence.order.OrderPersistence;
import br.com.api_order.domain.use_case.order.FindAllOrders;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllOrdersImpl implements FindAllOrders {

    private final OrderPersistence orderPersistence;

    @Override
    public List<OrderDomain> execute() {
        return orderPersistence.findAll();
    }
}
