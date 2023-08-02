package com.bingle.esports.controller;

import com.bingle.common.dto.ApiResponseDto;
import com.bingle.esports.dto.ESportsApiResponse;
import com.bingle.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/esports")
public class EsportsController {

    @Value("${PREFIX_OF_ESPORTS_SCHEDULE_API_URI}")
    private String prefixOfEsportsScheduleApiURI;

    @Value("${SUFFIX_OF_ESPORTS_SCHEDULE_API_URI}")
    private String suffixOfEsportsScheduleApiURI;

    private final MatchService matchService;

    @PostMapping("/sync")
    public Mono<ResponseEntity<ApiResponseDto>> sync(@RequestParam String month) {
        WebClient webClient = WebClient.create();
        String uri = prefixOfEsportsScheduleApiURI + month + suffixOfEsportsScheduleApiURI;

        return webClient.get()
                .uri(uri)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .bodyToMono(ESportsApiResponse.class)
                .map(eSportsApiResponse -> {
                    matchService.synchronize(eSportsApiResponse.getContent().getMatches());
                    return ResponseEntity.ok(ApiResponseDto.OK("OK"));
                });
    }
}
