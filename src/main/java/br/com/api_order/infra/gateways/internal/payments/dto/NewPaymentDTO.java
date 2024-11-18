package br.com.api_order.infra.gateways.internal.payments.dto;

import br.com.api_order.domain.entity.payment.enums.PaymentType;
import br.com.api_order.infra.gateways.internal.payments.dto.order.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPaymentDTO {

    private OrderDTO orderDTO;
    private PaymentType provider;
}
