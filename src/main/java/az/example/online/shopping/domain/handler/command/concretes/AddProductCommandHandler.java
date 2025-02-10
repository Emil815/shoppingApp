package az.example.online.shopping.domain.handler.command.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractProductCommandMapper;
import az.example.online.shopping.domain.roots.ProductRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.CategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.SubCategoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductCategoryRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductSubCategoryRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.AddProductCommand;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AddProductCommandHandler {
    private final ProductRepository productRepository;
    private final AbstractProductCommandMapper productCommandMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductResponseModel handle(AddProductCommand command, MultipartFile file) {
        ProductRoot productRoot = productCommandMapper.toRoot(command, file);
        CategoryEntity category = productCategoryRepository.findByName(command.getCategory())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategoryEntity subCategoryEntity = category.getSubCategories()
                .stream()
                .filter(entity -> entity.getName().equalsIgnoreCase(command.getSubCategory()))
                .findAny()
                .orElseThrow(() -> new RuntimeException("SubCategory not found"));
        ProductEntity product = this.productCommandMapper.toEntity(productRoot);
        product.setSubCategory(subCategoryEntity);
        return productCommandMapper.toResponseModel(productRepository.save(product));

    }
}
