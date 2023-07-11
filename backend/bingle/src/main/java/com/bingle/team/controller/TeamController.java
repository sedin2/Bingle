package com.bingle.team.controller;

import com.bingle.common.dto.ApiResponseDto;
import com.bingle.team.dto.TeamDto;
import com.bingle.team.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public ResponseEntity<ApiResponseDto> getTeams() {
        List<TeamDto> teams = teamService.findTeams();
        Map<String, List<TeamDto>> responseData = new HashMap<>();
        responseData.put("teams", teams);
        return ResponseEntity.ok(ApiResponseDto.OK(responseData));
    }
}
