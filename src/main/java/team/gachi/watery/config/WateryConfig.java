package team.gachi.watery.config;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(WateryProperties.class)
public class WateryConfig {
}
