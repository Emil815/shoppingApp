package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractBasketItemCommandMapper;
import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketItemRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.web.dto.response.BasketForModeratorResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.BasketItemResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BasketForModeratorQueryHandler {
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final AbstractBasketItemCommandMapper basketItemCommandMapper;

    public List<BasketForModeratorResponseModel> handle() {


        List<BasketEntity> basketEntityList = basketRepository
                .findByBasketStatusInAndIsActive(List.of(BasketStatus.ON_CONFIRMATION, BasketStatus.CONFIRMED), true);
        return basketEntityList
                .stream()
                .map(basket -> {
                            BasketForModeratorResponseModel model = new BasketForModeratorResponseModel();
                            model.setId(basket.getId().toString());
                            model.setBasketStatus(basket.getBasketStatus());
                            model.setTotalAmount(basket.getTotalAmount());
                            model.setUserPhoneNumber(basket.getUser().getPhoneNumber());
                            model.setProducts(basketItemList(basket.getId()));
                            return model;
                        }

                )
                .toList();


    }

    private List<BasketItemResponseModel> basketItemList(UUID basketId) {
        return basketItemRepository.findByBasketId(basketId)
                .stream()
                .map(this.basketItemCommandMapper::toResponseModel)
                .toList();
    }
}
