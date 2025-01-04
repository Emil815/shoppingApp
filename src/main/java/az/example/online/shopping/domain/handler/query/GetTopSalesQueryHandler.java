package az.example.online.shopping.domain.handler.query;

import az.example.online.shopping.domain.mapper.concretes.ProductCommandMapper;
import az.example.online.shopping.infrastructure.dataaccess.repository.SalesRepository;
import az.example.online.shopping.infrastructure.web.dto.response.ProductResponseModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTopSalesQueryHandler {
    private final SalesRepository salesRepository;
    private final ProductCommandMapper productCommandMapper;


    public Page<ProductResponseModel> handle(Integer top, Pageable pageable) {
        List<ProductResponseModel> bestSellingProducts = salesRepository.findBestSellingProducts(top)
                .stream()
                .map(productCommandMapper::toResponseModel)
                .toList();

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), bestSellingProducts.size());


        return new PageImpl<>(bestSellingProducts.subList(start, end), pageable, bestSellingProducts.size());
    }

}
