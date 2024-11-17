package az.example.online.shopping.infrastructure.web.service.abstracts;

import az.example.online.shopping.infrastructure.dataaccess.entity.UserEntity;
import az.example.online.shopping.infrastructure.web.dto.response.AuthResponseModel;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface AbstractJwtService {

    String generateToken(Map<String, Object> claims, String subject, boolean isAccessToken);

    String generateAccessToken(String subject);

    String generateRefreshToken(String subject);

    Claims extractAllClaims(String token);

    String extractUser(String token);

    UserDetails extractUser(HttpServletRequest request);

    UserEntity extractUserEntity(HttpServletRequest request);

    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

    boolean isTokenValid(String token, String username);

    boolean isTokenExpired(String token);

    AuthResponseModel validateRefreshTokenAndGenerateAccessToken(HttpServletRequest request,
                                                                 @NonNull HttpServletResponse response);
}
