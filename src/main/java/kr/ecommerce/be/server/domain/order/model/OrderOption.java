package kr.ecommerce.be.server.domain.order.model;

import jakarta.persistence.*;
import kr.ecommerce.be.server.exception.custom.ConflictException;
import lombok.*;

@Getter
@Entity
@Table(name = "order_option")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(nullable = false)
    private Integer size;

    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;

    @Column(name = "created_at", nullable = false)
    private String createdAt;

    @Column(name = "updated_at", nullable = false)
    private String updatedAt;

    @PrePersist
    @PreUpdate
    public void validateFields() {
        if (size == null || size < 0) {
            throw new IllegalArgumentException("size는 0 이상이어야 합니다.");
        }
        if (stockQuantity == null || stockQuantity < 0) {
            throw new IllegalArgumentException("stockQuantity는 0 이상이어야 합니다.");
        }
    }

    public void incrementStockQuantity(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("추가 수량은 1 이상이어야 합니다.");
        }
        this.stockQuantity += quantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("차감 수량은 1 이상이어야 합니다.");
        }
        if (this.stockQuantity < quantity) {
            throw new ConflictException("재고가 부족합니다. optionId=" + this.id);
        }
        this.stockQuantity -= quantity;
    }

    public void validateEnoughStock(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new ConflictException("재고가 부족합니다. optionId=" + this.id);
        }
    }
}
