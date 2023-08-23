package com.bingle.playerranking.model;

import com.bingle.player.model.Player;
import com.bingle.team.model.Team;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "player_ranking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerRanking {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "league_id")
    private String leagueId;

    @Column(name = "bracket")
    private String bracket;

    @Column(name = "group_name")
    private String groupName;

    @Column(name = "rank")
    private Integer rank;

    @Column(name = "wins")
    private Integer wins;

    @Column(name = "loses")
    private Integer loses;

    @Column(name = "draws")
    private Integer draws;

    @Column(name = "scores")
    private Integer scores;

    @Column(name = "win_rate")
    private Double winRate;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @OneToOne(mappedBy = "playerRanking")
    private PlayerAddInfo playerAddInfo;

    @OneToOne
    @JoinColumn(name = "fk_player_id")
    private Player player;

    @OneToOne
    @JoinColumn(name = "fk_team_id")
    private Team team;
}
