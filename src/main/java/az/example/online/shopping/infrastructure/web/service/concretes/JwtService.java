package az.example.online.shopping.infrastructure.web.service.concretes;

import az.example.online.shopping.infrastructure.web.service.abstracts.AbstractJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService implements AbstractJwtService {

  @Value("${jwt.secret}")
  private String secretKey;

  @Value("${jwt.expiration.access}")
  private long accessTokenExpiration;

  @Value("${jwt.expiration.refresh}")
  private long refreshTokenExpiration;

  private Key getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String generateToken(Map<String, Object> claims, String subject, boolean isAccessToken) {
    return Jwts.builder()
        .setClaims(claims)
        .setSubject(subject)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(
            new Date(
                System.currentTimeMillis()
                    + (isAccessToken ? accessTokenExpiration : refreshTokenExpiration)))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String generateAccessToken(String subject) {
    return generateToken(Map.of("type", "ACCESS"), subject, true);
  }

  public String generateRefreshToken(String subject) {
    return generateToken(Map.of("type", "REFRESH"), subject, false);
  }

  public Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public boolean isTokenValid(String token, String username) {
    return extractUsername(token).equals(username) && !isTokenExpired(token);
  }

  public boolean isTokenExpired(String token) {
    return extractAllClaims(token).getExpiration().before(new Date());
  }

  public String validateRefreshTokenAndGenerateAccessToken(String token) {
    try {
      Claims claims = extractAllClaims(token);
      String tokenType = claims.get("type", String.class);

      if ("REFRESH".equals(tokenType) && !isTokenExpired(token)) {
        String phoneNumber = extractUsername(token);
        return generateAccessToken(phoneNumber);
      } else {
        throw new IllegalArgumentException("Invalid or expired refresh token");
      }
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid refresh token", e);
    }
  }
}
