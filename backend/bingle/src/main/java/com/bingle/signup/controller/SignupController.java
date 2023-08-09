package com.bingle.signup.controller;

import com.bingle.account.service.AccountService;
import com.bingle.common.dto.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final AccountService accountService;

    @PostMapping("/nickname/check")
    public ResponseEntity<ApiResponseDto> checkDuplicatedNickname(@RequestBody String nickname) {
        boolean isAvailable = !accountService.isExistedNickname(nickname);
        return ResponseEntity.ok(ApiResponseDto.OK("isAvailable", isAvailable));
    }

    @PostMapping("/nickname")
    public ResponseEntity<ApiResponseDto> updateNickname(@RequestBody String nickname) {
        Long kakaoId = 1L;
        accountService.updateNickname(kakaoId, nickname);
        return ResponseEntity.ok(ApiResponseDto.OK("isAvailable"));
    }
}
