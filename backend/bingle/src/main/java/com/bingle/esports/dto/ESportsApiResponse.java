package com.bingle.esports.dto;

import com.bingle.match.dto.MatchDto;
import com.bingle.team.dto.TeamDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ESportsApiResponse {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("content")
    private Content content;

    @Getter
    public static class Content {

        @JsonProperty("teams")
        private List<TeamDto> teams;

        @JsonProperty("matches")
        private List<MatchDto> matches;
    }
}
