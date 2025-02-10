package az.example.online.shopping.infrastructure.web.dto.request.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddProductCommand {
    private String name;
    private String description;
    private String category;
    private String subCategory;
    private String note;
    private BigDecimal sellPrice;
    private BigDecimal wholeSalePrice;
    private Integer piecesOfPack;
    private String code;
    private String article;


}
