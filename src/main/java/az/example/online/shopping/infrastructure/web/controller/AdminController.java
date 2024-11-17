package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.comman.concretes.AddProductCommandHandler;
import az.example.online.shopping.domain.handler.comman.concretes.UpdateProductCommandHandler;
import az.example.online.shopping.infrastructure.web.dto.request.command.AddProductCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.UpdateProductCommand;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
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

    @PostMapping(value = "product/add",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponseModel> addProduct(
            @RequestPart("command") AddProductCommand command,
            @RequestPart("file") MultipartFile file
    ) {
        return new ResponseEntity<>(
                addProductCommandHandler.handle(command, file), HttpStatus.CREATED);

    }


    @PostMapping(value = "product/update",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponseModel> updateProduct(
            @RequestPart("command") UpdateProductCommand command,
            @RequestPart("file") MultipartFile file,
            HttpServletRequest request
    ) {
        return new ResponseEntity<>(
                updateProductCommandHandler.handle(command, file, request), HttpStatus.CREATED);

    }
}
