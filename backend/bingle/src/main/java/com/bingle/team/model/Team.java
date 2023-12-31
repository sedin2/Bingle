package com.bingle.team.model;

import com.bingle.match.model.Match;
import com.bingle.player.model.Player;
import com.bingle.teamranking.model.TeamRanking;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "teams")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "game_code")
    private String gameCode;

    @Column(name = "name")
    private String name;

    @Column(name = "name_acronym")
    private String nameAcronym;

    @Column(name = "name_eng")
    private String nameEng;

    @Column(name = "name_eng_acronym")
    private String nameEngAcronym;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "color_image_url")
    private String colorImageUrl;

    @Column(name = "white_image_url")
    private String whiteImageUrl;

    @Column(name = "black_image_url")
    private String blackImageUrl;

    @Column(name = "dss_white_image_url")
    private String dssWhiteImageUrl;

    @Column(name = "dss_black_image_url")
    private String dssBlackImageUrl;

    @Column(name = "order_point")
    private Long orderPoint;

    @OneToMany(mappedBy = "homeTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> homeMatches;

    @OneToMany(mappedBy = "awayTeam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Match> awayMatches;

    @OneToOne(mappedBy = "team")
    private TeamRanking teamRanking;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Player> players;

    @Builder
    private Team(Long id, String teamId, String gameCode, String name, String nameAcronym,
                 String nameEng, String nameEngAcronym, String imageUrl, String colorImageUrl,
                 String whiteImageUrl, String blackImageUrl, String dssWhiteImageUrl,
                 String dssBlackImageUrl, Long orderPoint, TeamRanking teamRanking) {
        this.id = id;
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
        this.homeMatches = new ArrayList<>();
        this.awayMatches = new ArrayList<>();
        this.teamRanking = teamRanking;
        this.players = new ArrayList<>();
    }
}
