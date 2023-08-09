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
@Transactional
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccessTokenRepository accessTokenRepository;

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

    @Transactional(readOnly = true)
    public boolean isExistedNickname(String nickname) {
        return accountRepository.existsByNickname(nickname);
    }

    public void updateNickname(Long kakaoId, String nickname) {
        if (isExistedNickname(nickname)) {
            throw new RuntimeException();
        }

        Account account = accountRepository.findByKakaoId(kakaoId).orElseThrow();
        account.updateNickname(nickname);
    }
}
