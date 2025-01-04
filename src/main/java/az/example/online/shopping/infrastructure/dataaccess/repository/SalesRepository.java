package az.example.online.shopping.infrastructure.dataaccess.repository;

import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.SalesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SalesRepository extends JpaRepository<SalesEntity, UUID> {
    Optional<SalesEntity> findByProductId(UUID productId);

    @Query(value = """
        SELECT p.* 
        FROM sales s
        JOIN products p ON s.product_id = p.id
        GROUP BY s.product_id, p.id
        ORDER BY SUM(s.quantity_sold) DESC
        LIMIT :top
    """, nativeQuery = true)
    List<ProductEntity> findBestSellingProducts(@Param("top") Integer top);

}
