package app.telegramgptbot.telegrambot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Data
@Component
@ConfigurationProperties(prefix = "openai.api")
public class OpenAiConfig {
    private String key;
    private String url;
    private String model;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
