package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.ChangeBasketStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmBasketCommandHandler {
    private final BasketRepository basketRepository;

    @Transactional
    public void handle(ChangeBasketStatusCommand command) {
        BasketEntity basketEntity = basketRepository.findById(UUID.fromString(command.getBasketId()))
                .filter(item -> item.getBasketStatus() == BasketStatus.ON_CONFIRMATION)
                .orElseThrow(() -> new RuntimeException("Basket not found"));
        basketEntity.setBasketStatus(BasketStatus.CONFIRMED);
        basketRepository.save(basketEntity);

    }
}
