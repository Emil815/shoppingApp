package az.example.online.shopping.infrastructure.web.dto.request.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveProductFromBasketCommand {
    private String productCode;
}
