package team.gachi.watery.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import team.gachi.watery.dto.ExceptionResponse;
import team.gachi.watery.exception.ExceptionCode;

import java.io.IOException;

@Component
public class CustomJwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().println(objectMapper.writeValueAsString(exceptionResponse()));
    }

    private ResponseEntity<ExceptionResponse> exceptionResponse() {
        return ResponseEntity
                .status(ExceptionCode.UNAUTHORIZED.getStatusCode())
                .body(ExceptionResponse.of(ExceptionCode.UNAUTHORIZED.getMessage()));
    }
}
