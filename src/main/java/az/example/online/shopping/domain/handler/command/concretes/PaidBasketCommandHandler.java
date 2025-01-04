package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketItemEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.SalesEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketItemRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.SalesRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.ChangeBasketStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaidBasketCommandHandler {
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final SalesRepository salesRepository;

    @Transactional
    public void handle(ChangeBasketStatusCommand command) {
        BasketEntity basketEntity = basketRepository.findById(UUID.fromString(command.getBasketId()))
                .filter(item -> Objects.equals(BasketStatus.CONFIRMED, item.getBasketStatus()))
                .orElseThrow(() -> new RuntimeException("Basket not found"));
        basketEntity.setBasketStatus(BasketStatus.PAID);
        basketRepository.save(basketEntity);

        List<BasketItemEntity> productEntityList = basketItemRepository
                .findByBasketId(basketEntity.getId());

        productEntityList
                .forEach(basketItem -> {
                    ProductEntity productEntity = basketItem.getProduct();
                    SalesEntity salesEntity = SalesEntity
                            .builder()
                            .product(productEntity)
                            .quantitySold(basketItem.getQuantity())
                            .build();
                    salesRepository.save(salesEntity);
                });


    }
}
