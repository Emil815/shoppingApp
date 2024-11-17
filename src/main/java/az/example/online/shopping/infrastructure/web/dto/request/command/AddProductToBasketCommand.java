package az.example.online.shopping.infrastructure.web.dto.request.command;

import az.example.online.shopping.domain.valueobjects.SalesType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddProductToBasketCommand {
    private String productCode;
    private Integer quantity;
    private SalesType salesType;
}
