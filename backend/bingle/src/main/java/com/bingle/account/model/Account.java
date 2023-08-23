package com.bingle.account.model;

import com.bingle.accountteamsubscription.model.AccountTeamSubscription;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "accounts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "accounts_id")
    private Long id;

    @Column(name = "kakao_id")
    private Long kakaoId;

    @Column(name = "connected_at")
    private LocalDateTime connectedAt;

    @Column(name = "email", unique = true, nullable = false, length = 45)
    private String email;

    @Column(name = "is_email_verified")
    private Boolean isEmailVerified;

    @Column(name = "nickname", length = 8)
    private String nickname;

    @Column(name = "is_account_active")
    private Boolean isAccountActive;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccessToken> accessTokens = new ArrayList<>();

    @OneToOne(mappedBy = "account")
    private Notification notification;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountTeamSubscription> subscribedTeams;

    @Builder
    private Account(Long id, Long kakaoId, LocalDateTime connectedAt, String email, Boolean isEmailVerified,
                   String nickname) {
        this.id = id;
        this.kakaoId = kakaoId;
        this.connectedAt = connectedAt;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.nickname = nickname;
        this.isAccountActive = false;
        this.accessTokens = new ArrayList<>();
    }

    public Account addAccessToken(AccessToken accessToken) {
        accessToken.addAccount(this);
        accessTokens.add(accessToken);
        return this;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
