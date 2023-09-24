package com.bingle.teamranking.model;

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
@Table(name = "team_add_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TeamAddInfo {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "kda")
    private Double kda;

    @Column(name = "kills")
    private Integer kills;

    @Column(name = "deaths")
    private Integer deaths;

    @Column(name = "assists")
    private Integer assists;

    @Column(name = "first_kill_rate")
    private Double firstKillRate;

    @Column(name = "first_tower_rate")
    private Double firstTowerRate;

    @Column(name = "first_baron_rate")
    private Double firstBaronRate;

    @OneToOne
    @JoinColumn(name = "team_ranking_id")
    private TeamRanking teamRanking;

    @Builder
    private TeamAddInfo(Long id, Double kda, Integer kills, Integer deaths, Integer assists,
                       Double firstKillRate, Double firstTowerRate, Double firstBaronRate, TeamRanking teamRanking) {
        this.id = id;
        this.kda = kda;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.firstKillRate = firstKillRate;
        this.firstTowerRate = firstTowerRate;
        this.firstBaronRate = firstBaronRate;
        this.teamRanking = teamRanking;
    }
}
