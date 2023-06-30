package com.bingle.account.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "access_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccessToken {

    @Id
    @GeneratedValue
    @Column(name = "access_token_id")
    private Long id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expires_in")
    private int expiresIn;

    @Column(name = "refresh_token_expires_in")
    private int refreshTokenExpiresIn;

    @OneToOne(mappedBy = "accessToken")
    private Account account;
}
