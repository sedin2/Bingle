package com.bingle.oauth;

import com.bingle.oauth.dto.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class OAuthController {

    @Value("${URL}")
    private String url;

    @Value("${GRANT_TYPE}")
    private String grantType;

    @Value("${CLIENT_ID}")
    private String clientId;

    @Value("${REDIRECT_URI}")
    private String redirectURI;

    @GetMapping("/oauth/callback/kakao")
    public ResponseEntity<Void> getCode(@RequestParam String code) {
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
                .subscribe(response -> System.out.println("Response: " + response));

        return ResponseEntity.ok().build();
    }
}
