package az.example.online.shopping.infrastructure.dataaccess.repository;

import az.example.online.shopping.domain.valueobjects.BasketStatus;
import az.example.online.shopping.infrastructure.dataaccess.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BasketRepository extends JpaRepository<BasketEntity, UUID> {
    Optional<BasketEntity> findByBasketStatusAndUserPhoneNumberAndIsActive(BasketStatus basketStatus, String phoneNumber, boolean isActive);
    List<BasketEntity> findByBasketStatusInAndIsActive(List<BasketStatus> basketStatus, boolean isActive);
}
