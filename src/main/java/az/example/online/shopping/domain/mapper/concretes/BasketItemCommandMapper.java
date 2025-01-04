package az.example.online.shopping.domain.mapper.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractBasketItemCommandMapper;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketItemEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.web.dto.response.BasketItemResponseModel;
import org.springframework.stereotype.Component;

@Component
public class BasketItemCommandMapper implements AbstractBasketItemCommandMapper {
    @Override
    public BasketItemResponseModel toResponseModel(BasketItemEntity basketItemEntity) {
        ProductEntity product = basketItemEntity.getProduct();
        return BasketItemResponseModel
                .builder()
                .name(product.getName())
                .category(product.getSubCategory().getCategory().getName())
                .subCategory(product.getSubCategory().getName())
                .code(product.getCode())
                .salesType(basketItemEntity.getSalesType())
                .price(basketItemEntity.getPrice())
                .quantity(basketItemEntity.getQuantity())
                .build();
    }
}
