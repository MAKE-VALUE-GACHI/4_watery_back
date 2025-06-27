package team.gachi.watery.config;


import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "watery")
public class WateryProperties {
    private final Secret secret;

    @Getter
    public static class Secret {
        private String key;

        public Secret(String key) {
            this.key = key;
        }

        @PostConstruct
        private void init() {
            this.key = Base64.getEncoder().encodeToString(key.getBytes(StandardCharsets.UTF_8));
        }
    }

}
