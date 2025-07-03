package team.gachi.watery.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.val;
import team.gachi.watery.auth.UserAuthentication;

import java.time.Instant;
import java.util.Date;

public class JwtUtil {
    private final Algorithm algorithm;
    private final long accessTokenValidationSecond;
    private final long refreshTokenValidationSecond;

    public JwtUtil(String secret,
                   Long accessTokenValidationSecond,
                   Long refreshTokenValidationSecond) {
        this.algorithm = Algorithm.HMAC256(secret);
        this.accessTokenValidationSecond = accessTokenValidationSecond;
        this.refreshTokenValidationSecond = refreshTokenValidationSecond;
    }

    public String generateAccessToken(Long userId) {
        val authentication = UserAuthentication.create(userId);
        return generateToken(authentication, accessTokenValidationSecond);
    }

    public String generateRefreshToken(Long userId) {
        val authentication = UserAuthentication.create(userId);
        return generateToken(authentication, refreshTokenValidationSecond);
    }

    private String generateToken(UserAuthentication authentication, Long tokenExpirationTime) {
        return JWT.create()
                .withClaim("userId", authentication.getUserId())
                .withIssuedAt(Date.from(Instant.now()))
                .withExpiresAt(Date.from(Instant.now().plusSeconds(tokenExpirationTime)))
                .sign(algorithm);
    }

    public JwtValidationType validateToken(String token) {
        try {
            getDecodedJWT(token);
            return JwtValidationType.VALID_JWT;
        } catch (TokenExpiredException e) {
            return JwtValidationType.EXPIRED_JWT;
        } catch (RuntimeException exception) {
            return JwtValidationType.INVALID_JWT;
        }
    }

    private DecodedJWT getDecodedJWT(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public Long getUserIdFromToken(String token) {
        return getDecodedJWT(token).getClaim("userId").asLong();
    }
}
