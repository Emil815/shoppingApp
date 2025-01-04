package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractBasketItemCommandMapper;
import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketItemEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketItemRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.web.dto.response.BasketResponseModel;
import az.example.online.shopping.infrastructure.web.service.concretes.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketQueryHandler {
    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final AbstractBasketItemCommandMapper basketItemCommandMapper;
    private final JwtService jwtService;

    public BasketResponseModel handle(HttpServletRequest request) {
        UserEntity user = jwtService.extractUserEntity(request);
        BasketEntity basketEntity = basketRepository.findByBasketStatusAndUserPhoneNumberAndIsActive(BasketStatus.DRAFT,user.getPhoneNumber(), true)
                .orElse(
                        basketRepository.save(BasketEntity
                                .builder()
                                .basketStatus(BasketStatus.DRAFT)
                                .totalAmount(BigDecimal.ZERO)
                                .user(user).build())
                );

        List<BasketItemEntity> list = basketItemRepository.findByBasketId(basketEntity.getId());
        return BasketResponseModel
                .builder()
                .basketStatus(basketEntity.getBasketStatus())
                .totalAmount(basketEntity.getTotalAmount())
                .products(list
                        .stream()
                        .map(basketItemCommandMapper::toResponseModel).toList())
                .build();


    }
}
