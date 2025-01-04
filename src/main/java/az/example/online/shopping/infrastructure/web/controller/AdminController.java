package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.command.concretes.*;
import az.example.online.shopping.infrastructure.web.dto.request.command.ProductCategoryCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.AddProductCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.ProductSubCategoryCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.UpdateProductCommand;
import az.example.online.shopping.infrastructure.web.dto.response.CategoryResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.SubCategoryResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
    private final AddProductCommandHandler addProductCommandHandler;
    private final UpdateProductCommandHandler updateProductCommandHandler;
    private final AddProductCategoryCommandHandler addProductCategoryCommandHandler;
    private final DeleteProductCategoryCommandHandler deleteProductCategoryCommandHandler;
    private final AddProductSubCategoryCommandHandler addProductSubCategoryCommandHandler;
    private final DeleteProductSubCategoryCommandHandler deleteProductSubCategoryCommandHandler;
    private final ObjectMapper objectMapper;

    @PostMapping(value = "product/add",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponseModel> addProduct(
            @RequestPart("command") String command,
            @RequestPart("file") MultipartFile file
    ) throws JsonProcessingException {
        AddProductCommand addProductCommand = objectMapper.readValue(command, AddProductCommand.class);
        return new ResponseEntity<>(
                addProductCommandHandler.handle(addProductCommand, file), HttpStatus.CREATED);

    }


    @PostMapping(value = "product/update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponseModel> updateProduct(
            @RequestPart("command") String command,
            @RequestPart("file") MultipartFile file,
            HttpServletRequest request
    ) throws JsonProcessingException {
        UpdateProductCommand updateProductCommand = objectMapper.readValue(command, UpdateProductCommand.class);
        return new ResponseEntity<>(
                updateProductCommandHandler.handle(updateProductCommand, file, request), HttpStatus.CREATED);

    }

    @PostMapping(value = "category/add")
    public ResponseEntity<CategoryResponseModel> addCategory(@RequestBody ProductCategoryCommand command) {
        return new ResponseEntity<>(addProductCategoryCommandHandler
                .handle(command), HttpStatus.CREATED);


    }

    @DeleteMapping(value = "category/delete")
    public ResponseEntity<CategoryResponseModel> deleteCategory(@RequestBody ProductCategoryCommand command) {
        return new ResponseEntity<>(deleteProductCategoryCommandHandler
                .handle(command), HttpStatus.ACCEPTED);


    }

    @PostMapping(value = "category/sub/add")
    public ResponseEntity<SubCategoryResponseModel> addSubCategory(@RequestBody ProductSubCategoryCommand command) {
        return new ResponseEntity<>(addProductSubCategoryCommandHandler
                .handle(command), HttpStatus.CREATED);


    }

    @DeleteMapping(value = "category/sub/delete")
    public ResponseEntity<SubCategoryResponseModel> deleteSubCategory(@RequestBody ProductSubCategoryCommand command) {
        return new ResponseEntity<>(deleteProductSubCategoryCommandHandler
                .handle(command), HttpStatus.ACCEPTED);


    }


}
