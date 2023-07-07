package com.bingle.team.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TeamDto {

    @JsonProperty("teamId")
    private String teamId;

    @JsonProperty("gameCode")
    private String gameCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("nameAcronym")
    private String nameAcronym;

    @JsonProperty("nameEng")
    private String nameEng;

    @JsonProperty("nameEngAcronym")
    private String nameEngAcronym;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("colorImageUrl")
    private String colorImageUrl;

    @JsonProperty("whiteImageUrl")
    private String whiteImageUrl;

    @JsonProperty("blackImageUrl")
    private String blackImageUrl;

    @JsonProperty("dssWhiteImageUrl")
    private String dssWhiteImageUrl;

    @JsonProperty("dssBlackImageUrl")
    private String dssBlackImageUrl;

    @JsonProperty("orderPoint")
    private Long orderPoint;
}
