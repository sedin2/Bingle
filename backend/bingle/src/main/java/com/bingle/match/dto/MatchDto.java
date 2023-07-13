package com.bingle.match.dto;

import com.bingle.team.dto.TeamDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
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
}
