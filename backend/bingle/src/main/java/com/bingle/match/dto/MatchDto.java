package com.bingle.match.dto;

import com.bingle.match.model.Match;
import com.bingle.team.dto.TeamDto;
import com.bingle.team.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MatchDto {

    @JsonProperty("gameId")
    private String gameId;

    @JsonProperty("leagueId")
    private String leagueId;

    @JsonProperty("topLeagueId")
    private String topLeagueId;

    @JsonProperty("gameCode")
    private String gameCode;

    @JsonProperty("stadium")
    private String stadium;

    @JsonProperty("startDate")
    private Long startDate;

    @JsonProperty("title")
    private String title;

    @JsonProperty("homeScore")
    private Integer homeScore;

    @JsonProperty("awayScore")
    private Integer awayScore;

    @JsonProperty("weeks")
    private Integer weeks;

    @JsonProperty("days")
    private Integer days;

    @JsonProperty("winner")
    private String winner;

    @JsonProperty("matchStatus")
    private String matchStatus;

    @JsonProperty("maxMatchCount")
    private Integer maxMatchCount;

    @JsonProperty("currentMatchSet")
    private Integer currentMatchSet;

    @JsonProperty("homeTeam")
    private TeamDto homeTeam;

    @JsonProperty("awayTeam")
    private TeamDto awayTeam;

    @Builder
    private MatchDto(String gameId, String leagueId, String topLeagueId, String gameCode, String stadium,
                     Long startDate, String title, Integer homeScore, Integer awayScore, Integer weeks,
                     Integer days, String winner, String matchStatus, Integer maxMatchCount,
                     Integer currentMatchSet, TeamDto homeTeam, TeamDto awayTeam) {
        this.gameId = gameId;
        this.leagueId = leagueId;
        this.topLeagueId = topLeagueId;
        this.gameCode = gameCode;
        this.stadium = stadium;
        this.startDate = startDate;
        this.title = title;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.weeks = weeks;
        this.days = days;
        this.winner = winner;
        this.matchStatus = matchStatus;
        this.maxMatchCount = maxMatchCount;
        this.currentMatchSet = currentMatchSet;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public static Match toEntity(MatchDto matchDto, Team homeTeam, Team awayTeam) {
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
    }

    public static MatchDto of(Match match) {
        return MatchDto.builder()
                .gameId(match.getGameId())
                .leagueId(match.getLeagueId())
                .topLeagueId(match.getTopLeagueId())
                .gameCode(match.getGameCode())
                .stadium(match.getStadium())
                .startDate(match.getStartDate())
                .title(match.getTitle())
                .homeScore(match.getHomeScore())
                .awayScore(match.getAwayScore())
                .weeks(match.getWeeks())
                .days(match.getDays())
                .winner(match.getWinner())
                .matchStatus(match.getMatchStatus())
                .maxMatchCount(match.getMaxMatchCount())
                .currentMatchSet(match.getCurrentMatchSet())
                .homeTeam(TeamDto.of(match.getHomeTeam()))
                .awayTeam(TeamDto.of(match.getAwayTeam()))
                .build();
    }
}
