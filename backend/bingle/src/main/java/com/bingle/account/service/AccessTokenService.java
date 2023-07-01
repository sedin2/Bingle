package com.bingle.account.service;

import com.bingle.account.dto.AccessTokenDto;
import com.bingle.account.model.AccessToken;
import com.bingle.account.repository.AccessTokenRepository;
import com.bingle.oauth.dto.AccessTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccessTokenService {

    private final AccessTokenRepository accessTokenRepository;

    @Transactional
    public AccessTokenDto createAccessToken(AccessTokenResponse accessTokenResponse) {
        AccessToken saved = accessTokenRepository.save(accessTokenResponse.toEntity());
        return AccessTokenDto.of(saved);
    }
}
