package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.infrastructure.dataaccess.entity.CategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.SubCategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductCategoryRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductSubCategoryRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.ProductSubCategoryCommand;
import az.example.online.shopping.infrastructure.web.dto.response.SubCategoryResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeleteProductSubCategoryCommandHandler {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductSubCategoryRepository productSubCategoryRepository;

    public SubCategoryResponseModel handle(ProductSubCategoryCommand command) {
        CategoryEntity category = productCategoryRepository.findByName(command.getMainCategoryName().toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        SubCategoryEntity subCategoryEntity = category.getSubCategories()
                .stream()
                .filter(entity -> entity.getName().equalsIgnoreCase(command.getName()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("SubCategory not found"));

        productSubCategoryRepository.delete(subCategoryEntity);
        return SubCategoryResponseModel
                .builder()
                .subCategoryNames(List.of(subCategoryEntity.getName()))
                .mainCategoryName(category.getName())
                .build();
    }
}
