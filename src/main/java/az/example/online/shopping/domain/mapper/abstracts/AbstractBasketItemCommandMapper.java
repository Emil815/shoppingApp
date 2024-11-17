package az.example.online.shopping.domain.mapper.abstracts;

import az.example.online.shopping.infrastructure.dataaccess.entity.BasketItemEntity;
import az.example.online.shopping.infrastructure.web.dto.response.BasketItemResponseModel;

public interface AbstractBasketItemCommandMapper {
    BasketItemResponseModel toResponseModel(BasketItemEntity basketItemEntity);
}
