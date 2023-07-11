package com.bingle.team.service;

import com.bingle.team.dto.TeamDto;
import com.bingle.team.model.Team;
import com.bingle.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public void addTeam(List<TeamDto> teamDtos) {
        List<Team> teams = teamDtos.stream()
                .map(teamDto -> Team.builder()
                        .teamId(teamDto.getTeamId())
                        .gameCode(teamDto.getGameCode())
                        .name(teamDto.getName())
                        .nameAcronym(teamDto.getNameAcronym())
                        .nameEng(teamDto.getNameEng())
                        .nameEngAcronym(teamDto.getNameEngAcronym())
                        .imageUrl(teamDto.getImageUrl())
                        .colorImageUrl(teamDto.getColorImageUrl())
                        .whiteImageUrl(teamDto.getWhiteImageUrl())
                        .blackImageUrl(teamDto.getBlackImageUrl())
                        .dssWhiteImageUrl(teamDto.getDssWhiteImageUrl())
                        .dssBlackImageUrl(teamDto.getDssBlackImageUrl())
                        .orderPoint(teamDto.getOrderPoint())
                        .build())
                .collect(Collectors.toList());
        teamRepository.saveAll(teams);
    }

    public List<TeamDto> findTeams() {
        return teamRepository.findAll().stream()
                .map(team -> TeamDto.builder()
                        .teamId(team.getTeamId())
                        .gameCode(team.getGameCode())
                        .name(team.getName())
                        .nameAcronym(team.getNameAcronym())
                        .nameEng(team.getNameEng())
                        .nameEngAcronym(team.getNameEngAcronym())
                        .imageUrl(team.getImageUrl())
                        .colorImageUrl(team.getColorImageUrl())
                        .whiteImageUrl(team.getWhiteImageUrl())
                        .blackImageUrl(team.getBlackImageUrl())
                        .dssWhiteImageUrl(team.getDssWhiteImageUrl())
                        .dssBlackImageUrl(team.getDssBlackImageUrl())
                        .orderPoint(team.getOrderPoint())
                        .build())
                .collect(Collectors.toList());
    }
}
