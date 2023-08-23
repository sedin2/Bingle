package com.bingle.account.dto;

import com.bingle.account.model.Account;
import com.bingle.team.dto.TeamDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
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
    private Boolean isAccountActive;

    @JsonIgnore
    private List<AccessTokenDto> accessTokens;

    private List<TeamDto> subscribedTeams;

    @Builder
    private AccountDto(Long id, Long kakaoId, LocalDateTime connectedAt, String email,
                       Boolean isEmailVerified, String nickname, Boolean isAccountActive,
                       List<AccessTokenDto> accessTokens, List<TeamDto> subscribedTeams) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.connectedAt = connectedAt;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.nickname = nickname;
        this.isAccountActive = isAccountActive;
        this.accessTokens = accessTokens;
        this.subscribedTeams = subscribedTeams;
    }

    public static AccountDto of(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .kakaoId(account.getKakaoId())
                .connectedAt(account.getConnectedAt())
                .email(account.getEmail())
                .isEmailVerified(account.getIsEmailVerified())
                .nickname(account.getNickname())
                .isAccountActive(account.getIsAccountActive())
                .accessTokens(account.getAccessTokens().stream()
                        .map(AccessTokenDto::of)
                        .collect(Collectors.toList()))
                .subscribedTeams(null)
                .build();
    }
}
