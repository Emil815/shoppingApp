package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.command.concretes.FilterProductsQueryHandler;
import az.example.online.shopping.domain.handler.query.GetAllProductsCategoryQueryHandler;
import az.example.online.shopping.domain.handler.query.GetAllSubCategoriesQueryHandler;
import az.example.online.shopping.domain.handler.query.GetSubCategoriesByCategoryQueryHandler;
import az.example.online.shopping.infrastructure.web.dto.response.CategoryResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.SubCategoryResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {

    private final FilterProductsQueryHandler filterProductsQueryHandler;
    private final GetAllProductsCategoryQueryHandler getAllProductsCategoryQueryHandler;
    private final GetSubCategoriesByCategoryQueryHandler getSubCategoriesByCategoryQueryHandler;
    private final GetAllSubCategoriesQueryHandler getAllSubCategoriesQueryHandler;

    @GetMapping("/filter")
    public Page<ProductResponseModel> filterProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String subCategory,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) Boolean isWholeSale,

            Pageable pageable) {
        return filterProductsQueryHandler.handle(name, category, subCategory, minPrice, maxPrice, pageable, isWholeSale);


    }

    @GetMapping(value = "category/all")
    public ResponseEntity<List<CategoryResponseModel>> getAllCategories() {
        return new ResponseEntity<>(getAllProductsCategoryQueryHandler.handle(), HttpStatus.OK);
    }


    @GetMapping(value = "category/sub/{mainCategory}")
    public ResponseEntity<SubCategoryResponseModel> getSubCategoriesByCategory(@PathVariable("mainCategory")
                                                                               String mainCategory) {
        return new ResponseEntity<>(getSubCategoriesByCategoryQueryHandler.handle(mainCategory), HttpStatus.OK);
    }

    @GetMapping(value = "category/sub/all")
    public ResponseEntity<List<SubCategoryResponseModel>> getAllSubCategories() {
        return new ResponseEntity<>(getAllSubCategoriesQueryHandler.handle(), HttpStatus.OK);
    }

}
