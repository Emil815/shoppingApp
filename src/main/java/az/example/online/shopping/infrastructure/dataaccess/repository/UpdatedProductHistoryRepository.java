package az.example.online.shopping.infrastructure.dataaccess.repository;

import az.example.online.shopping.infrastructure.dataaccess.entity.UpdatedProductHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UpdatedProductHistoryRepository extends JpaRepository<UpdatedProductHistoryEntity, UUID> {
}
