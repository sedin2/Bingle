package com.bingle.account.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription_status")
    private SubscriptionStatus subscriptionStatus;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccessToken> accessTokens = new ArrayList<>();

    @Builder
    private Account(Long id, Long kakaoId, LocalDateTime connectedAt, String email, Boolean isEmailVerified,
                   String nickname, SubscriptionStatus subscriptionStatus) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.connectedAt = connectedAt;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.nickname = nickname;
        this.subscriptionStatus = subscriptionStatus;
        this.accessTokens = new ArrayList<>();
    }

    public Account addAccessToken(AccessToken accessToken) {
        accessToken.addAccount(this);
        accessTokens.add(accessToken);
        return this;
    }
}
