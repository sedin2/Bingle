package com.bingle.match.controller;

import com.bingle.common.dto.ApiResponseDto;
import com.bingle.match.dto.MatchDto;
import com.bingle.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/matches")
public class MatchController {

    private final MatchService matchService;

    @GetMapping
    public ResponseEntity<ApiResponseDto> getMatches(@RequestParam("month") YearMonth month) {
        List<MatchDto> matches = matchService.findMatches(month);
        return ResponseEntity.ok(ApiResponseDto.OK("matches", matches));
    }
}
