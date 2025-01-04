package az.example.online.shopping.domain.handler.query;

import az.example.online.shopping.infrastructure.dataaccess.entity.SubCategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductCategoryRepository;
import az.example.online.shopping.infrastructure.web.dto.response.SubCategoryResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetAllSubCategoriesQueryHandler {
    private final ProductCategoryRepository productCategoryRepository;

    public List<SubCategoryResponseModel> handle() {
        return productCategoryRepository
                .findAll()
                .stream()
                .map(entity -> {
                    SubCategoryResponseModel subCategoryResponseModel = new SubCategoryResponseModel();
                    subCategoryResponseModel.setMainCategoryName(entity.getName());
                    subCategoryResponseModel.setSubCategoryNames(entity
                            .getSubCategories()
                            .stream()
                            .map(SubCategoryEntity::getName)
                            .toList());
                    return subCategoryResponseModel;
                })
                .toList();
    }
}
