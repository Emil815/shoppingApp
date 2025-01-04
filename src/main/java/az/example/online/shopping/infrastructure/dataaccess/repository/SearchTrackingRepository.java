package az.example.online.shopping.infrastructure.dataaccess.repository;

import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.SearchTrackingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SearchTrackingRepository  extends JpaRepository<SearchTrackingEntity, UUID> {
    Optional<SearchTrackingEntity> findByProductId(UUID productId);

    @Query(value = """
        SELECT p.*
        FROM search_tracking st
        JOIN products p ON st.product_id = p.id
        ORDER BY st.search_count DESC
        LIMIT :top
    """, nativeQuery = true)
    List<ProductEntity> findMostSearchedProducts(@Param("top") Integer top);


}
