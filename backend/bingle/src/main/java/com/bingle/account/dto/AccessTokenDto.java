package com.bingle.account.dto;

import com.bingle.account.model.AccessToken;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AccessTokenDto {

    private Long id;

    private String accessToken;

    private String refreshToken;

    private Integer expiresIn;

    private Integer refreshTokenExpiresIn;

    @Builder
    private AccessTokenDto(Long id, String accessToken, String refreshToken,
                           Integer expiresIn, Integer refreshTokenExpiresIn) {
        this.id = id;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public static AccessTokenDto of(AccessToken accessToken) {
        return AccessTokenDto.builder()
                .id(accessToken.getId())
                .accessToken(accessToken.getAccessToken())
                .refreshToken(accessToken.getRefreshToken())
                .expiresIn(accessToken.getExpiresIn())
                .refreshTokenExpiresIn(accessToken.getRefreshTokenExpiresIn())
                .build();
    }
}
