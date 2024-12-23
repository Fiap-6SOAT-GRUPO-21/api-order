package br.com.api_order.application.dtos.store.payment;

import br.com.api_order.domain.entity.payment.enums.PaymentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MercadoPagoGatewayDTO {

    @Schema(description = "id Mercado Pago Gateway", example = "d8897bbb-868c-4163-b4c8-2e6baf356683")
    private UUID id;

    @Schema(description = "Payment type", example = "MERCADO_PAGO")
    private PaymentType type = PaymentType.MERCADO_PAGO;

    @Schema(description = "Collectors ID by MercadoPago", example = "1650421194")
    private String collectors;

    @Schema(description = "External POS ID by MercadoPago", example = "SUC001POS001")
    private String externalPos;
}
