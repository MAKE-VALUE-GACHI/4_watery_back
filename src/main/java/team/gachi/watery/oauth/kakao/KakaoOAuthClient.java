package team.gachi.watery.oauth.kakao;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import team.gachi.watery.exception.ExceptionCode;
import team.gachi.watery.exception.WateryException;
import team.gachi.watery.oauth.kakao.dto.KakaoResponse;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Slf4j
@Component
public class KakaoOAuthClient {
    private final static String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    public String getKakaoData(final String accessToken) {
        KakaoResponse response = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(AUTHORIZATION, accessToken)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new WateryException(ExceptionCode.SERVICE_AVAILABLE)))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new WateryException(ExceptionCode.SERVICE_AVAILABLE)))
                .bodyToMono(KakaoResponse.class)
                .block();

        if (response == null) {
            throw new WateryException(ExceptionCode.SERVICE_AVAILABLE);
        }

        return response.id();
    }
}
