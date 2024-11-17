package az.example.online.shopping.domain.handler.comman.concretes;

import az.example.online.shopping.domain.mapper.concretes.ProductCommandMapper;
import az.example.online.shopping.domain.specification.ProductSpecification;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductRepository;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponse;
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

    public Page<ProductResponse> handle(String name,
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
        return productRepository.findAll(spec, pageable)
                .map(this.productCommandMapper::toResponseModel);
    }


}
