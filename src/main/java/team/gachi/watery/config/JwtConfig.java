package team.gachi.watery.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import team.gachi.watery.util.JwtUtil;

@Configuration
public class JwtConfig {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.access-token.expiration-second}")
    private long accessTokenExpirationSecond;

    @Value("${jwt.refresh-token.expiration-second}")
    private long refreshTokenExpirationSecond;

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret,
                accessTokenExpirationSecond,
                refreshTokenExpirationSecond);
    }
}
