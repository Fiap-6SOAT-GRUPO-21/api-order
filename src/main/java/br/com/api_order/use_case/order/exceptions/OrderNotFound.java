package br.com.api_order.use_case.order.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Order not found")
public class OrderNotFound extends RuntimeException {

    public OrderNotFound() {
        super("Order not exists");
    }

}
