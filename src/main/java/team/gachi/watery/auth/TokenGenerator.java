package team.gachi.watery.auth;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenGenerator {
    private final SecretKeyFactory secretKeyFactory;

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000 * 2L;           // 2시간
    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 1000 * 24 * 14L;    // 2주

    public String generateAccessToken(long userId) {
        val authentication = UserAuthentication.create(userId);
        return generateToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    public String generateRefreshToken(long userId) {
        val authentication = UserAuthentication.create(userId);
        return generateToken(authentication, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    private String generateToken(Authentication authentication, Long tokenExpirationTime) {
        val now = new Date();

        val claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpirationTime));

        claims.put("userId", authentication.getPrincipal());

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(secretKeyFactory.create())
                .compact();
    }
}
