package com.bingle.account.dto;

import com.bingle.account.model.AccessToken;
import com.bingle.account.model.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AccountDto {

    @JsonIgnore
    private Long id;

    @JsonIgnore
    private Long kakaoId;

    @JsonIgnore
    private LocalDateTime connectedAt;

    private String email;

    @JsonIgnore
    private Boolean isEmailVerified;

    private String nickname;

    @JsonIgnore
    private List<AccessToken> accessTokens;

    @Builder
    private AccountDto(Long id, Long kakaoId, LocalDateTime connectedAt,
                       String email, Boolean isEmailVerified, String nickname, List<AccessToken> accessTokens) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.connectedAt = connectedAt;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.nickname = nickname;
        this.accessTokens = accessTokens;
    }

    public static AccountDto of(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .kakaoId(account.getKakaoId())
                .connectedAt(account.getConnectedAt())
                .email(account.getEmail())
                .isEmailVerified(account.getIsEmailVerified())
                .nickname(account.getNickname())
                .accessTokens(account.getAccessTokens())
                .build();
    }
}
