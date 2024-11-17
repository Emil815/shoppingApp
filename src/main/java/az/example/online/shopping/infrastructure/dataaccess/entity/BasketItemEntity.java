package az.example.online.shopping.infrastructure.dataaccess.entity;

import az.example.online.shopping.domain.valueobjects.SalesType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "basket_items")
public class BasketItemEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "basket_id", nullable = false)
    private BasketEntity basket;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SalesType salesType;
}
