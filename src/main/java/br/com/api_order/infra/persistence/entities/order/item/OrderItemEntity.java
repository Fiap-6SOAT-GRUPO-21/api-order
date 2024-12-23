package br.com.api_order.infra.persistence.entities.order.item;

import br.com.api_order.infra.persistence.entities.order.OrderEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity(name = "order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderItemEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @NotNull
    @Column(nullable = false)
    private int quantity;

    @Column
    private String observation;

    // ---------------------------------------- RELACIONAMENTOS ----------------------------------

    @Column(name = "ID_PRODUCT", nullable = false)
    private UUID idProduct;

    @Column(name = "ID_ORDER", nullable = false)
    private UUID idOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID", insertable = false, updatable = false)
    private OrderEntity order;
}
