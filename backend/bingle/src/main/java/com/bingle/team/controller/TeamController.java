package com.bingle.team.controller;

import com.bingle.common.dto.ApiResponseDto;
import com.bingle.team.dto.TeamDto;
import com.bingle.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<TeamDto>>> getTeams() {
        List<TeamDto> teams = teamService.findTeams();
        log.info("teamsteams {}", teams);
        return ResponseEntity.ok(ApiResponseDto.OK("teams", teams));
    }
}
