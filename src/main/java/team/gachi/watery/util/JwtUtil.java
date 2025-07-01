package team.gachi.watery.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.http.HttpStatus;
import team.gachi.watery.exception.WateryException;

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

    public String encodeUserId(Long userId) {
        return JWT.create()
                .withClaim("userId", userId)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenValidationSecond))
                .sign(algorithm);
    }

    public String encodeUuid(String uuid) {
        return JWT.create()
                .withClaim("uuid", uuid)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenValidationSecond))
                .sign(algorithm);
    }

    public Long decodeToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT verify = verifier.verify(token);

            return verify.getClaim("userId").asLong();
            } catch (TokenExpiredException exception) {
            throw new WateryException(HttpStatus.UNAUTHORIZED, "만료된 토큰입니다.");
        } catch (JWTDecodeException exception) {
            throw new WateryException(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 디코딩에 실패했습니다.");
        }
    }

    public String decodeRefreshToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();

            DecodedJWT verify = verifier.verify(token);

            return verify.getClaim("uuid").asString();
            } catch (TokenExpiredException exception) {
            throw new WateryException(HttpStatus.UNAUTHORIZED, "만료된 리프레시 토큰입니다.");
        } catch (JWTDecodeException exception) {
            throw new WateryException(HttpStatus.INTERNAL_SERVER_ERROR, "토큰 디코딩에 실패했습니다.");
        }
    }
}
