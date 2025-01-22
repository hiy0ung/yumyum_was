package org.koreait.yumyum.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private final Key key;

    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    @Value("${mail.auth-code-expiration-millis}")
    private int jwtEmailExpirationMs;

    public int getExpiration() {
        return jwtExpirationMs;
    }

    public int getEmailExpiration() {
        return jwtEmailExpirationMs;
    }

    public JwtProvider(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") int jwtExpirationMs) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.jwtExpirationMs = jwtExpirationMs;
    }
    public String generateJwtToken(Long id) {
        return Jwts.builder()
                .claim("id", id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateEmailValidToken(String userId) {
        return Jwts.builder()
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEmailExpirationMs))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
    public String validateAndGetUserEmail(String token) {
        if (token == null || token.isEmpty()) {
            throw new IllegalArgumentException("토큰이 비어 있습니다.");
        }

        Claims claims;
        try {
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build();
            claims = jwtParser.parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }

        // claim에서 이메일(userId) 추출
        Object emailObj = claims.get("userId");
        if (emailObj == null) {
            throw new IllegalArgumentException("토큰에 userId가 없습니다.");
        }

        // 이메일(String) 반환
        return emailObj.toString();
    }

    public String removeBearer(String bearerToken) {
        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid JWT token format");
        }
        return bearerToken.substring("Bearer ".length());
    }

    public Long getIdFromJwt(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            // 만료 시간 체크
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public Claims getClaims(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        return jwtParser.parseClaimsJws(token).getBody();
    }
    // 초기화 로그
    @PostConstruct
    public void init() {
        System.out.println("JWT Provider 초기화 완료");
    }
}
