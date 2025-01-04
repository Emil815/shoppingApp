package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketItemEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketItemRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.RemoveProductFromBasketCommand;
import az.example.online.shopping.infrastructure.web.service.abstracts.AbstractJwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RemoveProductFromBasketCommandHandler {
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final AbstractJwtService jwtService;

    @Transactional
    public void handle(RemoveProductFromBasketCommand command, HttpServletRequest request) {
        UserEntity user = jwtService.extractUserEntity(request);
        BasketEntity basketEntity = basketRepository.findByBasketStatusAndUserPhoneNumberAndIsActive(BasketStatus.DRAFT, user.getPhoneNumber(), true)
                .orElseThrow(() -> new RuntimeException("Basket not found"));

        List<BasketItemEntity> basketItemEntityList =
                this.basketItemRepository.findByBasketId(basketEntity.getId());
        BasketItemEntity basketItemEntity = basketItemEntityList
                .stream()
                .filter(item -> item.getProduct().getCode().equals(command.getProductCode()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Basket item not found"));
        basketEntity.setTotalAmount(basketEntity.getTotalAmount().subtract(basketItemEntity.getPrice()));
        basketRepository.save(basketEntity);
        basketItemRepository.delete(basketItemEntity);

    }
}
