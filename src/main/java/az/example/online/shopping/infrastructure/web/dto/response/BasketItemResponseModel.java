package az.example.online.shopping.infrastructure.web.dto.response;

import az.example.online.shopping.domain.valueobjects.SalesType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketItemResponseModel {
    private String name;

    private String category;

    private String subCategory;


    private String code;

    private SalesType salesType;

    private BigDecimal price;

    private Integer quantity;


}
