package com.bingle.filter;

import com.bingle.common.dto.ApiResponseCode;
import com.bingle.common.dto.ApiResponseDto;
import com.bingle.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BearerTokenFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = extractBearerToken(request);

        if (token == null) {
            sendUnauthorizedResponse(response, "Bearer Token is missing");
            return;
        }

        Long kakaoId = extractKakaoIdFromToken(token);
        request.setAttribute("kakaoId", kakaoId);

        filterChain.doFilter(request, response);
    }

    private String extractBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && JwtUtil.isBearerScheme(bearerToken)) {
            return bearerToken.substring(7);
        }

        return null;
    }

    private void sendUnauthorizedResponse(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");

        String responseBody = objectMapper.writeValueAsString(ApiResponseDto.ERROR(ApiResponseCode.UNAUTHORIZED, message));
        response.getWriter().write(responseBody);
    }

    private Long extractKakaoIdFromToken(String token) {
        Claims claims = JwtUtil.parseToken(token);
        Long kakaoId = Long.valueOf(claims.getSubject());
        return kakaoId;
    }
}
