package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.command.concretes.*;
import az.example.online.shopping.infrastructure.web.dto.request.command.AddProductToBasketCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.RemoveProductFromBasketCommand;
import az.example.online.shopping.infrastructure.web.dto.request.command.UpdateProductFromBasketCommand;
import az.example.online.shopping.infrastructure.web.dto.response.BasketResponseModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/api/basket")
public class BasketController {


    private final AddProductToBasketCommandHandler addProductToBasketCommandHandler;
    private final UpdateProductFromBasketCommandHandler updateProductFromBasketCommandHandler;
    private final RemoveProductFromBasketCommandHandler removeProductFromBasketCommandHandler;
    private final SendToApproveBasketCommandHandler sendToApproveBasketCommandHandler;
    private final BasketQueryHandler basketQueryHandler;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody AddProductToBasketCommand command,
                                             HttpServletRequest request) {
        addProductToBasketCommandHandler.handle(command, request);
        return ResponseEntity.ok("Successfully added product to Basket");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProductQuantity(
            @RequestBody UpdateProductFromBasketCommand command, HttpServletRequest request) {
        updateProductFromBasketCommandHandler.handle(command, request);
        return ResponseEntity.ok("Successfully updated product quantity");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> removeProduct(
            @RequestBody RemoveProductFromBasketCommand command, HttpServletRequest request) {
        removeProductFromBasketCommandHandler.handle(command, request);
        return ResponseEntity.ok("Successfully removed product from Basket");
    }

    @PostMapping("/sendToApprove")
    public ResponseEntity<String> sendToApprove(HttpServletRequest request) {
        sendToApproveBasketCommandHandler.handle(request);
        return ResponseEntity.ok("Successfully sent to approve");
    }

    @GetMapping("/query")
    public ResponseEntity<BasketResponseModel> query(HttpServletRequest request) {
        return ResponseEntity.ok(basketQueryHandler.handle(request));
    }
}
