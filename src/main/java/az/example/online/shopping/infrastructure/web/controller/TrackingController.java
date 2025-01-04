package az.example.online.shopping.infrastructure.web.controller;

import az.example.online.shopping.domain.handler.query.GetTopSalesQueryHandler;
import az.example.online.shopping.domain.handler.query.GetTopSearchQueryHandler;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/track")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrackingController {
    private final GetTopSalesQueryHandler getTopSalesQueryHandler;
    private final GetTopSearchQueryHandler getTopSearchQueryHandler;


    @GetMapping("/best-sellers/{top}")
    public Page<ProductResponseModel> bestSellers(@PathVariable Integer top, Pageable pageable) {
        return getTopSalesQueryHandler.handle(top, pageable);
    }

    @GetMapping("/most-searched/{top}")
    public Page<ProductResponseModel> mostSearched(@PathVariable Integer top, Pageable pageable) {
        return getTopSearchQueryHandler.handle(top, pageable);
    }
}
