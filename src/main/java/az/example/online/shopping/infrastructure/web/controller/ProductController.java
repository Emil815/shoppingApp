package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.comman.concretes.FilterProductsQueryHandler;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final FilterProductsQueryHandler filterProductsQueryHandler;

    @GetMapping("/filter")
    public Page<ProductResponse> filterProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subCategory,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean isWholeSale,

            Pageable pageable) {
        return filterProductsQueryHandler.handle(name, category, subCategory, minPrice, maxPrice, pageable, isWholeSale);


    }

}
