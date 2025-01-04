package az.example.online.shopping.domain.handler.query;

import az.example.online.shopping.infrastructure.dataaccess.entity.BaseEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.SubCategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductSubCategoryRepository;
import az.example.online.shopping.infrastructure.web.dto.response.SubCategoryResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetSubCategoriesByCategoryQueryHandler {
    private final ProductSubCategoryRepository productSubCategoryRepository;

    public SubCategoryResponseModel handle(String mainCategory) {
        List<String> subCategories = productSubCategoryRepository
                .findByCategoryName(mainCategory.toUpperCase())
                .stream()
                .filter(BaseEntity::getIsActive)
                .map(SubCategoryEntity::getName
                ).toList();

        return new SubCategoryResponseModel(mainCategory, subCategories);
    }
}
