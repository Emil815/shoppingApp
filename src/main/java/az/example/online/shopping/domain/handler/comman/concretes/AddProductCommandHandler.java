package az.example.online.shopping.domain.handler.comman.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractProductCommandMapper;
import az.example.online.shopping.domain.roots.ProductRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductRepository;
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

    public ProductResponseModel handle(AddProductCommand command, MultipartFile file) {
        ProductRoot productRoot = productCommandMapper.toRoot(command, file);
        ProductEntity savedProduct = productRepository.save(this.productCommandMapper.toEntity(productRoot));
        return productCommandMapper.toResponseModel(savedProduct);

    }
}
