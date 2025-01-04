package az.example.online.shopping.infrastructure.dataaccess.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", nullable = false)
    private SubCategoryEntity subCategory;

    @Column(nullable = false)
    private BigDecimal sellPrice;

    @Column(nullable = false)
    private BigDecimal wholeSalePrice;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imageType;

    @Column(nullable = false)
    private byte[] imageData;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SalesEntity> sales;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private SearchTrackingEntity searchTracking;

    private String note;
    private String description;
    private String article;
}
