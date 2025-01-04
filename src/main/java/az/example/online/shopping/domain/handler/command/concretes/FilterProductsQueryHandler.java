package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.mapper.concretes.ProductCommandMapper;
import az.example.online.shopping.domain.specification.ProductSpecification;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.SearchTrackingEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.SearchTrackingRepository;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class FilterProductsQueryHandler {
    private final ProductRepository productRepository;
    private final ProductCommandMapper productCommandMapper;
    private final SearchTrackingRepository searchTrackingRepository;

    public Page<ProductResponseModel> handle(String name,
                                             String category,
                                             String subCategory,
                                             BigDecimal minPrice,
                                             BigDecimal maxPrice,
                                             Pageable pageable,
                                             Boolean isWholeSale) {
        Specification<ProductEntity> spec = Specification.where(
                ProductSpecification.hasName(name)
                        .and(ProductSpecification.hasCategory(category))
                        .and(ProductSpecification.hasSubCategory(subCategory))
                        .and(ProductSpecification.hasPriceBetween(minPrice, maxPrice, isWholeSale))
        );
        Page<ProductEntity> products = productRepository.findAll(spec, pageable);
        if (name != null) {
            products.stream()
                    .filter(productEntity -> productEntity.getName().contains(name))
                    .findAny()
                    .ifPresent(productEntity -> {
                        SearchTrackingEntity searchTracking = searchTrackingRepository.findByProductId(productEntity.getId())
                                .orElse(SearchTrackingEntity.builder()
                                        .product(productEntity)
                                        .searchCount(0)
                                        .build());
                        searchTracking.setSearchCount(searchTracking.getSearchCount() + 1);
                        searchTrackingRepository.save(searchTracking);
                    });
        }
        return productRepository.findAll(spec, pageable)
                .map(this.productCommandMapper::toResponseModel);
    }


}
