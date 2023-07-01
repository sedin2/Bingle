package com.bingle.account.dto;

import com.bingle.account.model.AccessToken;
import com.bingle.account.model.Account;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AccountDto {

    private Long id;

    private Long kakaoId;

    private LocalDateTime connectedAt;

    private String email;

    private Boolean isEmailVerified;

    private String nickname;

    private AccessToken accessToken;

    @Builder
    private AccountDto(Long id, Long kakaoId, LocalDateTime connectedAt,
                       String email, Boolean isEmailVerified, String nickname, AccessToken accessToken) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.connectedAt = connectedAt;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.nickname = nickname;
        this.accessToken = accessToken;
    }

    public static AccountDto of(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .kakaoId(account.getKakaoId())
                .connectedAt(account.getConnectedAt())
                .email(account.getEmail())
                .isEmailVerified(account.getIsEmailVerified())
                .nickname(account.getNickname())
                .accessToken(account.getAccessToken())
                .build();
    }
}
