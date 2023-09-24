package com.bingle.teamranking.model;

import com.bingle.team.model.Team;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "team_ranking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamRanking {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

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

    @OneToOne(mappedBy = "teamRanking")
    private TeamAddInfo teamAddInfo;

    @OneToOne
    @JoinColumn(name = "fk_team_id")
    private Team team;

    @Builder
    private TeamRanking(Long id, String teamId, String leagueId, String bracket, String groupName,
                        Integer rank, Integer wins, Integer loses, Integer draws, Integer scores,
                        Double winRate, TeamAddInfo teamAddInfo, Team team) {
        this.id = id;
        this.teamId = teamId;
        this.leagueId = leagueId;
        this.bracket = bracket;
        this.groupName = groupName;
        this.rank = rank;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
        this.scores = scores;
        this.winRate = winRate;
        this.teamAddInfo = teamAddInfo;
        this.team = team;
    }
}
