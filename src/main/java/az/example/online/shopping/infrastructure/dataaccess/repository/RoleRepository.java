package az.example.online.shopping.infrastructure.dataaccess.repository;


import az.example.online.shopping.infrastructure.dataaccess.entity.RoleEntity;
import az.example.online.shopping.domain.valueobjects.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(RoleEnum name);
}
