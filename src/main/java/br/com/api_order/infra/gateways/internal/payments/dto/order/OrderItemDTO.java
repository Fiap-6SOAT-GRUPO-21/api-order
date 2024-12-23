package br.com.api_order.infra.gateways.internal.payments.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {

    private UUID idProduct;
    private String productName;
    private BigDecimal productPrice;
    private UUID idOrder;
    private int quantity;
}
