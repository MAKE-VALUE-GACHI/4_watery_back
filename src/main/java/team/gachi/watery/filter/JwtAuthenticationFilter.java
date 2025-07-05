package team.gachi.watery.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import team.gachi.watery.auth.UserAuthentication;
import team.gachi.watery.util.JwtUtil;
import team.gachi.watery.util.JwtValidationType;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    private static final String TOKEN_HEADER_NAME = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws IOException, ServletException {
        String token = getJwtFromRequest(request);

        if (isValidToken(token)) {
            Long userId = jwtUtil.getUserIdFromToken(token);
            UserAuthentication authentication = new UserAuthentication(userId, null, null);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidToken(String token) {
        return StringUtils.hasText(token) && jwtUtil.validateToken(token) == JwtValidationType.VALID_JWT;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(TOKEN_HEADER_NAME);
        return (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX))
                ? bearerToken.substring(TOKEN_PREFIX.length())
                : null;
    }
}
