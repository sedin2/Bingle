package com.bingle.oauth;

import com.bingle.account.dto.AccessTokenDto;
import com.bingle.account.dto.AccountDto;
import com.bingle.account.service.AccessTokenService;
import com.bingle.account.service.AccountService;
import com.bingle.common.dto.ApiResponseDto;
import com.bingle.oauth.dto.AccessTokenResponse;
import com.bingle.oauth.dto.KakaoUserInformationResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth/callback")
public class OAuthController {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER_SCHEME = "Bearer ";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    private static final String REDIRECT_URI = "redirect_uri";
    private static final String CODE = "code";

    @Value("${OAUTH_TOKEN_URI}")
    private String oauthTokenURI;

    @Value("${GRANT_TYPE}")
    private String grantType;

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${REDIRECT_URI}")
    private String redirectURI;

    @Value("${USER_INFORMATION_URI}")
    private String userInformationURI;

    private final AccountService accountService;

    private final AccessTokenService accessTokenService;

    @GetMapping("/kakao")
    public Mono<ResponseEntity<ApiResponseDto>> getCode(@RequestParam String code) {
        WebClient webClient = WebClient.create();

        return webClient.post()
                .uri(oauthTokenURI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData(createRequestBody(code)))
                .retrieve()
                .bodyToMono(AccessTokenResponse.class)
                .flatMap(accessTokenResponse -> getUserInformation(webClient, accessTokenResponse))
                .map(accountDto -> {
                    LinkedHashMap<String, String> mockToken = new LinkedHashMap<>();
                    mockToken.put("accessToken", "blahblah");
                    mockToken.put("refreshToken", "blahblah");
                    ApiResponseDto responseDto = ApiResponseDto.OK(mockToken);
                    return ResponseEntity.ok(responseDto);
                });
    }

    private MultiValueMap<String, String> createRequestBody(String code) {
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();

        requestBody.add(GRANT_TYPE, grantType);
        requestBody.add(CLIENT_ID, clientId);
        requestBody.add(REDIRECT_URI, redirectURI);
        requestBody.add(CODE, code);

        return requestBody;
    }

    private Mono<AccountDto> getUserInformation(WebClient webClient, AccessTokenResponse accessTokenResponse) {
        return webClient.get()
                .uri(userInformationURI)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .header(AUTHORIZATION, BEARER_SCHEME + accessTokenResponse.getAccessToken())
                .retrieve()
                .bodyToMono(KakaoUserInformationResponse.class)
                .flatMap(kakaoUserInformationResponse -> {
                    AccessTokenDto accessTokenDto = accessTokenService.createAccessToken(accessTokenResponse);
                    return Mono.just(accountService.createAccount(accessTokenDto, kakaoUserInformationResponse));
                });
    }
}
