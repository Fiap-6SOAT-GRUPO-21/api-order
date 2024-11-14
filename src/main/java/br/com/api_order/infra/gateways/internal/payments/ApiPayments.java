package br.com.api_order.infra.gateways.internal.payments;

import br.com.api_order.application.dtos.payment.PaymentDTO;
import br.com.api_order.application.dtos.payment.PaymentRequestDTO;
import br.com.api_order.infra.interceptor.DefaultInterceptor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "internal-api-payments",
        url = "${api.url.api-payment}",
        configuration = DefaultInterceptor.class
)
public interface ApiPayments {

    @PostMapping("/payments")
    PaymentDTO createPayment(PaymentRequestDTO request);

}
