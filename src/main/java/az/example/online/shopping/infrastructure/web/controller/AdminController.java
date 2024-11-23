package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.comman.concretes.AddProductCommandHandler;
import az.example.online.shopping.domain.handler.comman.concretes.UpdateProductCommandHandler;
import az.example.online.shopping.infrastructure.web.dto.request.command.AddProductCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.UpdateProductCommand;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
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
}
