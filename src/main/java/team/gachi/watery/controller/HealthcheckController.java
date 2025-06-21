package team.gachi.watery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health Check", description = "서버 상태 확인용 API")
@RestController
public class HealthcheckController {

    @Operation(summary = "헬스 체크", description = "서버가 정상적으로 작동 중인지 확인")
    @GetMapping("/health")
    public String healthcheck() {
        return "ok";
    }
}
