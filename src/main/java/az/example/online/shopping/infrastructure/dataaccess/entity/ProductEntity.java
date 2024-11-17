package az.example.online.shopping.infrastructure.dataaccess.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "products")
public class ProductEntity extends BaseEntity {
    private String name;
    private String description;

    private String category;

    private String subCategory;
    private String note;
    private BigDecimal sellPrice;
    private BigDecimal wholeSalePrice;
    private Integer quantity;
    private String imageName;
    private String imageType;

    private byte[] imageData;
}
