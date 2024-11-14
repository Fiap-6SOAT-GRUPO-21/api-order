package br.com.api_order.application.dtos.payment;

import br.com.api_order.domain.entity.payment.enums.PaymentStatus;
import br.com.api_order.domain.entity.payment.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentRequestDTO {

    private UUID idOrder;

    private BigDecimal amount;

    private PaymentType type;

    private String qrCode;

    private PaymentStatus status;

}
