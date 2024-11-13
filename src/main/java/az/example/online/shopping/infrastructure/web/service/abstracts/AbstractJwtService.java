package az.example.online.shopping.infrastructure.web.service.abstracts;

import io.jsonwebtoken.Claims;

import java.util.Map;
import java.util.function.Function;

public interface AbstractJwtService {

  String generateToken(Map<String, Object> claims, String subject, boolean isAccessToken);

  String generateAccessToken(String subject);

  String generateRefreshToken(String subject);

  Claims extractAllClaims(String token);

  String extractUsername(String token);

  <T> T extractClaim(String token, Function<Claims, T> claimsResolver);

  boolean isTokenValid(String token, String username);

  boolean isTokenExpired(String token);

  String validateRefreshTokenAndGenerateAccessToken(String token);
}
