package az.example.online.shopping.domain.handler.comman.concretes;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.domain.valueobjects.SalesType;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketItemEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketItemRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.AddProductToBasketCommand;
import az.example.online.shopping.infrastructure.web.service.abstracts.AbstractJwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AddProductToBasketCommandHandler {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final BasketItemRepository basketItemRepository;
    private final AbstractJwtService jwtService;

    @Transactional
    public void handle(AddProductToBasketCommand command, HttpServletRequest request) {
        UserEntity user = jwtService.extractUserEntity(request);
        BasketEntity basketEntity = basketRepository.findByBasketStatusAndUserPhoneNumberAndIsActive(BasketStatus.DRAFT,user.getPhoneNumber(), true)
                .orElse(
                        basketRepository.save(BasketEntity
                                .builder()
                                .basketStatus(BasketStatus.DRAFT)
                                .totalAmount(BigDecimal.ZERO)
                                .user(user).build())
                );
        ProductEntity productEntity = productRepository.findByCodeAndIsActive(command.getProductCode(), true)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        BasketItemEntity basketItemEntity = BasketItemEntity
                .builder()
                .basket(basketEntity)
                .price(calculatePrice(productEntity, command.getQuantity(), command.getSalesType()))
                .quantity(command.getQuantity())
                .product(productEntity)
                .salesType(command.getSalesType())
                .build();
        basketEntity.setTotalAmount(basketEntity.getTotalAmount().add(basketItemEntity.getPrice()));
        basketRepository.save(basketEntity);
        basketItemRepository.save(basketItemEntity);

    }

    private BigDecimal calculatePrice(ProductEntity product, Integer quantity, SalesType salesType) {

        return salesType == SalesType.RETAIL ? product.getSellPrice().multiply(BigDecimal.valueOf(quantity)) :
                product.getWholeSalePrice().multiply(BigDecimal.valueOf(quantity));

    }
}
