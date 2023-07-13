package com.bingle.match.service;

import com.bingle.match.dto.MatchDto;
import com.bingle.match.model.Match;
import com.bingle.match.repository.MatchRepository;
import com.bingle.team.model.Team;
import com.bingle.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public void addMatch(List<MatchDto> matchDtos) {
        List<Match> matches = matchDtos.stream()
                .map(matchDto -> {
                    Team homeTeam = teamRepository.findByTeamId(matchDto.getHomeTeam().getTeamId()).orElseThrow();
                    Team awayTeam = teamRepository.findByTeamId(matchDto.getAwayTeam().getTeamId()).orElseThrow();

                    return Match.builder()
                            .gameId(matchDto.getGameId())
                            .leagueId(matchDto.getLeagueId())
                            .topLeagueId(matchDto.getTopLeagueId())
                            .gameCode(matchDto.getGameCode())
                            .stadium(matchDto.getStadium())
                            .startDate(matchDto.getStartDate())
                            .title(matchDto.getTitle())
                            .homeScore(matchDto.getHomeScore())
                            .awayScore(matchDto.getAwayScore())
                            .weeks(matchDto.getWeeks())
                            .days(matchDto.getDays())
                            .winner(matchDto.getWinner())
                            .matchStatus(matchDto.getMatchStatus())
                            .maxMatchCount(matchDto.getMaxMatchCount())
                            .currentMatchSet(matchDto.getCurrentMatchSet())
                            .homeTeam(homeTeam)
                            .awayTeam(awayTeam)
                            .build();
                })
                .collect(Collectors.toList());

        matchRepository.saveAll(matches);
    }
}
