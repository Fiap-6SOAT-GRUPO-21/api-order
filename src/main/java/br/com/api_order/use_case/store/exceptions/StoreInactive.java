package br.com.api_order.use_case.store.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Store inactive")
public class StoreInactive extends RuntimeException {

    public StoreInactive() {
        super("Store inactive");
    }
}
