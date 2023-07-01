package com.bingle.account.model;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "account")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "connected_at")
    private LocalDateTime connectedAt;

    @Column(name = "email", unique = true, nullable = false, length = 45)
    private String email;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "nickname", nullable = false, length = 8)
    private String nickname;

    @OneToOne
    @JoinColumn(name = "access_token_id")
    private AccessToken accessToken;

    @Builder
    private Account(Long id, Long kakaoId, LocalDateTime connectedAt,
                    String email, Boolean isEmailVerified, String nickname, AccessToken accessToken) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.connectedAt = connectedAt;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.nickname = nickname;
        this.accessToken = accessToken;
    }

    public void allocateAccessToken(AccessToken accessToken) {
        this.accessToken = accessToken;
    }
}
