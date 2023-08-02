package com.bingle.match.service;

import com.bingle.match.dto.MatchDto;
import com.bingle.match.model.Match;
import com.bingle.match.repository.MatchRepository;
import com.bingle.team.model.Team;
import com.bingle.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MatchService {

    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    public void addMatch(List<MatchDto> matchDtos) {
        List<Match> matches = matchDtos.stream()
                .map(matchDto -> {
                    Team homeTeam = teamRepository.findByTeamId(matchDto.getHomeTeam().getTeamId()).orElseThrow();
                    Team awayTeam = teamRepository.findByTeamId(matchDto.getAwayTeam().getTeamId()).orElseThrow();
                    return MatchDto.toEntity(matchDto, homeTeam, awayTeam);
                })
                .collect(Collectors.toList());

        matchRepository.saveAll(matches);
    }

    public List<MatchDto> findMatches(YearMonth month) {
        LocalDate Month = month.atDay(1);
        LocalDate nextMonth = month.plusMonths(1).atDay(1);
        Long monthTimestamp = Month.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
        Long nextMonthTimestamp = nextMonth.atStartOfDay().atZone(ZoneOffset.UTC).toInstant().toEpochMilli();
        return matchRepository.findByStartDate(monthTimestamp, nextMonthTimestamp)
                .stream()
                .map(match -> MatchDto.of(match))
                .collect(Collectors.toList());
    }

    public void synchronize(List<MatchDto> newMatches) {
        List<Match> matches = matchRepository.findAllByMatchStatusOrMatchStatus("BEFORE", "STARTED");
        List<MatchDto> endMatches = newMatches.stream()
                .filter(matchDto -> matchDto.getMatchStatus().equals("RESULT"))
                .collect(Collectors.toList());

        matches.stream()
                .map(match -> endMatches.stream()
                        .filter(endMatchDto -> endMatchDto.getGameId().equals(match.getGameId()))
                        .findFirst()
                        .map(matchDto -> match.synchronize(matchDto))
                        .orElse(match))
                .collect(Collectors.toList());
    }
}
