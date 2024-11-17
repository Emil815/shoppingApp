package az.example.online.shopping.domain.specification;

import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<ProductEntity> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                name == null ? null : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<ProductEntity> hasCategory(String category) {
        return (root, query, criteriaBuilder) ->
                category == null ? null : criteriaBuilder.equal(root.get("category"), category);
    }

    public static Specification<ProductEntity> hasSubCategory(String subCategory) {
        return (root, query, criteriaBuilder) ->
                subCategory == null ? null : criteriaBuilder.equal(root.get("subCategory"), subCategory);
    }

    public static Specification<ProductEntity> hasPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Boolean isWholeSale) {
        return (root, query, criteriaBuilder) -> {
            if ((minPrice == null && maxPrice == null) || isWholeSale == null) return null;
            if (minPrice != null && maxPrice != null) {
                return criteriaBuilder.between(root.get(isWholeSale ? "wholeSalePrice" : "sellPrice"), minPrice, maxPrice);
            }
            if (minPrice != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(isWholeSale ? "wholeSalePrice" : "sellPrice"), minPrice);
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get(isWholeSale ? "wholeSalePrice" : "sellPrice"), maxPrice);
        };
    }
}
