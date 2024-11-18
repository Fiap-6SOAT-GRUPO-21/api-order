package br.com.api_order.infra.gateways.internal.payments;

import br.com.api_order.application.dtos.payment.PaymentDTO;
import br.com.api_order.infra.gateways.internal.payments.dto.NewPaymentDTO;
import br.com.api_order.infra.interceptor.DefaultInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@FeignClient(name = "internal-api-payments",
        url = "${api.url.api-payment}",
        configuration = DefaultInterceptor.class
)
public interface ApiPayments {

    @PostMapping("/payment")
    PaymentDTO createPayment(NewPaymentDTO request);

    @GetMapping("/payment/{id}")
    PaymentDTO findById(@PathVariable("id") UUID id);
}
