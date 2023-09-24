package com.bingle.team.dto;

import com.bingle.team.model.Team;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
    private TeamDto(String teamId, String gameCode, String name, String nameAcronym, String nameEng,
                    String nameEngAcronym, String imageUrl, String colorImageUrl, String whiteImageUrl,
                    String blackImageUrl, String dssWhiteImageUrl, String dssBlackImageUrl, Long orderPoint) {
        this.teamId = teamId;
        this.gameCode = gameCode;
        this.name = name;
        this.nameAcronym = nameAcronym;
        this.nameEng = nameEng;
        this.nameEngAcronym = nameEngAcronym;
        this.imageUrl = imageUrl;
        this.colorImageUrl = colorImageUrl;
        this.whiteImageUrl = whiteImageUrl;
        this.blackImageUrl = blackImageUrl;
        this.dssWhiteImageUrl = dssWhiteImageUrl;
        this.dssBlackImageUrl = dssBlackImageUrl;
        this.orderPoint = orderPoint;
    }

    public static Team toEntity(TeamDto teamDto) {
        return Team.builder()
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
                .build();
    }

    public static TeamDto of(Team team) {
        return TeamDto.builder()
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
                .build();
    }
}
