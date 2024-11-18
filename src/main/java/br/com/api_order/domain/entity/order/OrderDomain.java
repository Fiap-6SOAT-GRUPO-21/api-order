package br.com.api_order.domain.entity.order;

import br.com.api_order.application.dtos.product.ProductDTO;
import br.com.api_order.application.dtos.store.StoreDTO;
import br.com.api_order.domain.entity.DomainEntity;
import br.com.api_order.domain.entity.order.enums.StatusOrder;
import br.com.api_order.domain.entity.order.item.OrderItemDomain;
import br.com.api_order.domain.use_case.product.FindProductById;
import br.com.api_order.domain.use_case.product.FindProductByIdAndIdStore;
import br.com.api_order.domain.use_case.store.FindStoreById;
import br.com.api_order.use_case.order.exceptions.EmptyOrderItems;
import br.com.api_order.use_case.order.item.exceptions.EmptyQuantityItems;
import br.com.api_order.use_case.product.exceptions.ProductNotFound;
import br.com.api_order.use_case.store.exceptions.StoreInactive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDomain extends DomainEntity {

    private List<OrderItemDomain> items;
    private StatusOrder status;
    private BigDecimal total;
    private UUID idCustomer;
    private UUID idStore;
    private UUID idPayment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void validatedStore(FindStoreById findStoreById) {
        StoreDTO storeResponse = findStoreById.execute(this.getIdStore());

        if (!storeResponse.isActive())
            throw new StoreInactive();

        this.setIdStore(storeResponse.getId());
    }

    public void validatedQuantityItems(List<OrderItemDomain> items) {
        if (items.isEmpty()) {
            throw new EmptyOrderItems();
        }

        if (items.stream().anyMatch(item -> item.getQuantity() == 0)) {
            throw new EmptyQuantityItems();
        }
    }

    public void validatedItemOrException(FindProductByIdAndIdStore findProductByIdAndIdStore) {
        this.getItems().forEach(item -> {
            ProductDTO product = findProductByIdAndIdStore.execute(item.getIdProduct(), this.getIdStore());
            item.setProduct(product);
        });
    }

    public void calculateTotal(FindProductById findProductById) {
        this.setTotal(this.getItems().stream()
                .map(item -> findProductById.execute(item.getIdProduct())
                        .getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal::add)
                .orElseThrow(ProductNotFound::new));
    }

}
