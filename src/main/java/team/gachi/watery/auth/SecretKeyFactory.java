package team.gachi.watery.auth;


import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Component;
import team.gachi.watery.config.WateryProperties;

import javax.crypto.SecretKey;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class SecretKeyFactory {
    private final WateryProperties wateryProperties;

    public SecretKey create() {
        val encodedKey = Base64.getEncoder().encodeToString(wateryProperties.getSecret().getKey().getBytes());
        return Keys.hmacShaKeyFor(encodedKey.getBytes());
    }
}
