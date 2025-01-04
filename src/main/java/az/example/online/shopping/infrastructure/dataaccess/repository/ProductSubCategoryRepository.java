package az.example.online.shopping.infrastructure.dataaccess.repository;

import az.example.online.shopping.infrastructure.dataaccess.entity.SubCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<SubCategoryEntity, UUID> {
    List<SubCategoryEntity> findByCategoryName(String categoryName);

}
