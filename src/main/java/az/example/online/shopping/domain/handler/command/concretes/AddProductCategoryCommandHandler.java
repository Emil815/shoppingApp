package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.infrastructure.dataaccess.entity.CategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductCategoryRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.ProductCategoryCommand;
import az.example.online.shopping.infrastructure.web.dto.response.CategoryResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddProductCategoryCommandHandler {
    private final ProductCategoryRepository productCategoryRepository;

    public CategoryResponseModel handle(ProductCategoryCommand command) {
        productCategoryRepository.save(CategoryEntity
                .builder()
                .name(command.getName().toUpperCase())
                .build());
        return CategoryResponseModel
                .builder()
                .name(command.getName())
                .build();
    }
}
