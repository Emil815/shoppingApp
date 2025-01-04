package az.example.online.shopping.domain.handler.query;

import az.example.online.shopping.infrastructure.dataaccess.entity.BaseEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductCategoryRepository;
import az.example.online.shopping.infrastructure.web.dto.response.CategoryResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllProductsCategoryQueryHandler {
    private final ProductCategoryRepository productCategoryRepository;

    public List<CategoryResponseModel> handle() {
        return productCategoryRepository.findAll()
                .stream()
                .filter(BaseEntity::getIsActive)
                .map(entity ->
                        CategoryResponseModel
                                .builder()
                                .name(entity.getName())
                                .build()
                )
                .toList();
    }
}
