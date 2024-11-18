package br.com.api_order.domain.entity.order.item;

import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.domain.entity.DomainEntity;
import br.com.api_order.domain.entity.order.OrderDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDomain extends DomainEntity {

    private UUID idProduct;

    private ProductDTO product;

    private UUID idOrder;

    private OrderDomain order;

    private int quantity;

    private String observation;
}
