package az.example.online.shopping.domain.handler.comman.concretes;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.BasketRepository;
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

    @Transactional
    public void handle(ChangeBasketStatusCommand command) {
        BasketEntity basketEntity = basketRepository.findById(UUID.fromString(command.getBasketId()))
                .filter(item -> Objects.equals(BasketStatus.CONFIRMED, item.getBasketStatus()))
                .orElseThrow(() -> new RuntimeException("Basket not found"));
        basketEntity.setBasketStatus(BasketStatus.PAID);
        basketRepository.save(basketEntity);

    }
}
