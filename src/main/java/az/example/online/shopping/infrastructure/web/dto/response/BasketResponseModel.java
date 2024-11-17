package az.example.online.shopping.infrastructure.web.dto.response;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BasketResponseModel {



    private BigDecimal totalAmount;


    private BasketStatus basketStatus;


    List<BasketItemResponseModel> products;
}
