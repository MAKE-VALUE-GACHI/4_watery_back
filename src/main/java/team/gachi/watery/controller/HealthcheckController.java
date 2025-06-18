package team.gachi.watery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {

    @GetMapping("/health")
    public String healthcheck() {
        return "ok";
    }
}
