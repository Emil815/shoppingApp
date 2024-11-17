package az.example.online.shopping.infrastructure.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse  {
    private String name;
    private String code;
    private String article;
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
