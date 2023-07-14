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
                .map(teamDto -> TeamDto.toEntity(teamDto))
                .collect(Collectors.toList());

        String tbd = "TBD";

        Team undefinedTeam = Team.builder()
                .teamId(tbd)
                .gameCode(null)
                .name(tbd)
                .nameAcronym(tbd)
                .nameEng(tbd)
                .nameEngAcronym(tbd)
                .imageUrl(null)
                .colorImageUrl(null)
                .whiteImageUrl(null)
                .blackImageUrl(null)
                .dssWhiteImageUrl(null)
                .dssBlackImageUrl(null)
                .orderPoint(0L)
                .build();

        teams.add(undefinedTeam);
        teamRepository.saveAll(teams);
    }

    public List<TeamDto> findTeams() {
        return teamRepository.findAll().stream()
                .map(team -> TeamDto.of(team))
                .collect(Collectors.toList());
    }
}
