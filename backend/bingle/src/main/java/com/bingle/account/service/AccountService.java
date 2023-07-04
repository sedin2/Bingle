package com.bingle.account.service;

import com.bingle.account.dto.AccessTokenDto;
import com.bingle.account.dto.AccountDto;
import com.bingle.account.model.AccessToken;
import com.bingle.account.model.Account;
import com.bingle.account.repository.AccessTokenRepository;
import com.bingle.account.repository.AccountRepository;
import com.bingle.oauth.dto.KakaoUserInformationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccessTokenRepository accessTokenRepository;

    @Transactional
    public AccountDto createAccount(AccessTokenDto accessTokenDto,
                                    KakaoUserInformationResponse kakaoUserInformationResponse) {
        AccessToken accessToken = accessTokenRepository.findById(accessTokenDto.getId()).orElseThrow();

        Account account = accountRepository.findByKakaoId(kakaoUserInformationResponse.getId())
                .orElseGet(() -> Account.builder()
                        .kakaoId(kakaoUserInformationResponse.getId())
                        .connectedAt(kakaoUserInformationResponse.getConnectedAt())
                        .email(kakaoUserInformationResponse.getKakaoAccount().getEmail())
                        .isEmailVerified(kakaoUserInformationResponse.getKakaoAccount().getIsEmailVerified())
                        .nickname(kakaoUserInformationResponse.getKakaoAccount().getProfile().getNickname())
                        .build())
                .addAccessToken(accessToken);

        return AccountDto.of(accountRepository.save(account));
    }
}
