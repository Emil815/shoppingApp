package az.example.online.shopping.domain.handler.query;

import az.example.online.shopping.infrastructure.dataaccess.entity.BaseEntity;
import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.dataaccess.repository.ProductCategoryRepository;
import az.example.online.shopping.infrastructure.dataaccess.repository.UserRepository;
import az.example.online.shopping.infrastructure.web.dto.response.CategoryResponseModel;
import az.example.online.shopping.infrastructure.web.dto.response.UserInfoResponseModel;
import az.example.online.shopping.infrastructure.web.service.concretes.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoQueryHandler {
    private final JwtService jwtService;

    public UserInfoResponseModel handle(HttpServletRequest request) {
        UserEntity user = jwtService.extractUserEntity(request);
        return UserInfoResponseModel
                .builder()
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .isWholeSale(user.getIsWholeSale())
                .build();
    }
}
