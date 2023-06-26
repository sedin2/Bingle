package com.bingle.oauth;

import com.bingle.common.dto.ApiResponseDto;
import com.bingle.oauth.dto.AccessTokenResponse;
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

import java.util.LinkedHashMap;

@Slf4j
@RestController
@RequestMapping("/oauth/callback")
public class OAuthController {

    @Value("${URL}")
    private String url;

    @Value("${GRANT_TYPE}")
    private String grantType;

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${REDIRECT_URI}")
    private String redirectURI;

    @GetMapping("/kakao")
    public ResponseEntity<ApiResponseDto> getCode(@RequestParam String code) {
        WebClient webClient = WebClient.create();

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("client_id", clientId);
        requestBody.add("redirect_uri", redirectURI);
        requestBody.add("code", code);

        webClient.post()
                .uri(url)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData(requestBody))
                .retrieve()
                .bodyToMono(AccessTokenResponse.class)
                .subscribe(response -> log.debug("kakao AccessToken", response));

        LinkedHashMap<String, String> mockToken = new LinkedHashMap<>();
        mockToken.put("accessToken", "blahblah");
        mockToken.put("refreshToken", "blahblah");
        return ResponseEntity.ok(ApiResponseDto.OK(mockToken));
    }
}
