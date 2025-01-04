package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.infrastructure.dataaccess.entity.CategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductCategoryRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.ProductCategoryCommand;
import az.example.online.shopping.infrastructure.web.dto.response.CategoryResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteProductCategoryCommandHandler {
    private final ProductCategoryRepository productCategoryRepository;

    public CategoryResponseModel handle(ProductCategoryCommand command) {
        CategoryEntity category = productCategoryRepository.findByName(command.getName().toUpperCase())
                .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        productCategoryRepository.delete(category);
        return CategoryResponseModel
                .builder()
                .name(command.getName())
                .build();
    }
}
