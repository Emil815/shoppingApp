package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.web.service.abstracts.AbstractJwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendToApproveBasketCommandHandler {
    private final BasketRepository basketRepository;
    private final AbstractJwtService jwtService;

    @Transactional
    public void handle(HttpServletRequest request) {
        UserEntity user = jwtService.extractUserEntity(request);
        BasketEntity basketEntity = basketRepository.findByBasketStatusAndUserPhoneNumberAndIsActive(BasketStatus.DRAFT, user.getPhoneNumber(), true)
                .orElseThrow(() -> new RuntimeException("Basket not found"));
        basketEntity.setBasketStatus(BasketStatus.ON_CONFIRMATION);
        basketRepository.save(basketEntity);

    }
}
