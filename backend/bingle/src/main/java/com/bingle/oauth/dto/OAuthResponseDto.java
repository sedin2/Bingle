package com.bingle.oauth.dto;

import com.bingle.account.dto.AccountDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class OAuthResponseDto {

    private boolean isAccountActive;

    private AccountDto account;

    private String accessToken;

    private String refreshToken;

    @Builder
    private OAuthResponseDto(boolean isAccountActive, AccountDto account, String accessToken, String refreshToken) {
        this.isAccountActive = isAccountActive;
        this.account = account;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
