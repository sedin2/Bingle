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
    public AccountDto createAccount(AccessTokenDto accessTokenDto, KakaoUserInformationResponse userInformationResponse) {
        Account account = Account.builder()
                .kakaoId(userInformationResponse.getId())
                .connectedAt(userInformationResponse.getConnectedAt())
                .email(userInformationResponse.getKakaoAccount().getEmail())
                .isEmailVerified(userInformationResponse.getKakaoAccount().getIsEmailVerified())
                .nickname(userInformationResponse.getKakaoAccount().getProfile().getNickname())
                .build();

        AccessToken accessToken = accessTokenRepository.findById(accessTokenDto.getId()).orElseThrow();

        account.allocateAccessToken(accessToken);
        accountRepository.save(account);

        return AccountDto.of(account);
    }
}
