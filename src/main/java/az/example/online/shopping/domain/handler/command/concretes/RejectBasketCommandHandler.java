package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.ChangeBasketStatusCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RejectBasketCommandHandler {
    private final BasketRepository basketRepository;

    @Transactional
    public void handle(ChangeBasketStatusCommand command) {
        BasketEntity basketEntity = basketRepository.findById(UUID.fromString(command.getBasketId()))
                .filter(item -> List.of(BasketStatus.ON_CONFIRMATION, BasketStatus.CONFIRMED).contains(item.getBasketStatus()))
                .orElseThrow(() -> new RuntimeException("Basket not found"));
        basketEntity.setBasketStatus(BasketStatus.REJECTED);
        basketRepository.save(basketEntity);

    }
}
