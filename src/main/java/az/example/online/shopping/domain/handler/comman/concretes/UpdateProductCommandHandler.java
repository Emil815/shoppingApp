package az.example.online.shopping.domain.handler.comman.concretes;

import az.example.online.shopping.domain.mapper.abstracts.AbstractProductCommandMapper;
import az.example.online.shopping.domain.roots.ProductRoot;
import az.example.online.shopping.infrastructure.dataaccess.entity.ProductEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.UpdatedProductHistoryEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.UpdatedProductHistoryRepository;
import az.example.online.shopping.infrastructure.web.dto.request.command.UpdateProductCommand;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponse;
import az.example.online.shopping.infrastructure.web.service.abstracts.AbstractJwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class UpdateProductCommandHandler {
    private final ProductRepository productRepository;
    private final UpdatedProductHistoryRepository updatedProductHistoryRepository;
    private final AbstractProductCommandMapper productCommandMapper;
    private final AbstractJwtService jwtService;

    public ProductResponse handle(UpdateProductCommand command, MultipartFile file, HttpServletRequest request) {
        ProductRoot productRoot = productCommandMapper.toRoot(command, file);
        ProductEntity fetchedProduct = productRepository.findByCode(command.getCode())
                .map(item -> {

                    item.setIsActive(Boolean.FALSE);
                    return item;
                })
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.save(fetchedProduct);
        ProductEntity productEntity = this.productCommandMapper.toEntity(productRoot);
        ProductEntity savedProduct = productRepository.save(productEntity);
        UpdatedProductHistoryEntity updatedProductHistoryEntity = UpdatedProductHistoryEntity
                .builder()
                .updatedToId(savedProduct.getId())
                .updatedFromId(fetchedProduct.getId())
                .author(jwtService.extractUsername(request))
                .build();
        updatedProductHistoryRepository.save(updatedProductHistoryEntity);
        return productCommandMapper.toResponseModel(savedProduct);

    }
}
