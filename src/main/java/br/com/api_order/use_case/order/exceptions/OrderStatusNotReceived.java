package br.com.api_order.use_case.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order status not received, no possible to update")
public class OrderStatusNotReceived extends RuntimeException {

    public OrderStatusNotReceived() {
        super("Order status not received, no possible to update");
    }

}
