package com.bingle.signup.controller;

import com.bingle.account.service.AccountService;
import com.bingle.common.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SignupController {

    private final AccountService accountService;

    @PostMapping("/nickname/check")
    public ResponseEntity<ApiResponseDto> checkDuplicatedNickname(HttpServletRequest request, @RequestBody String nickname) {
        Long kakaoId = (Long) request.getAttribute("kakaoId");
        log.info("KakaoId {}", kakaoId);
        boolean isAvailable = !accountService.isExistedNickname(nickname);
        return ResponseEntity.ok(ApiResponseDto.OK("isAvailable", isAvailable));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto> updateNickname(HttpServletRequest request, @RequestBody String nickname) {
        Long kakaoId = (Long) request.getAttribute("kakaoId");
        accountService.updateNickname(kakaoId, nickname);
        return ResponseEntity.ok(ApiResponseDto.OK("isAvailable"));
    }
}
