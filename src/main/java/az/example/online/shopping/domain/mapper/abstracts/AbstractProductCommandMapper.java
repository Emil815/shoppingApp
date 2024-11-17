package az.example.online.shopping.domain.mapper.abstracts;

import az.example.online.shopping.domain.roots.ProductRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.web.dto.request.command.AddProductCommand;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

public interface AbstractProductCommandMapper {
    ProductRoot toRoot(AddProductCommand command, MultipartFile file);
    ProductResponse toResponseModel(ProductEntity entity);
    ProductEntity toEntity(ProductRoot root);
}
