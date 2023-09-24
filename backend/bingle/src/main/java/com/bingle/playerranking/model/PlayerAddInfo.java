package com.bingle.playerranking.model;

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
@Table(name = "player_add_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlayerAddInfo {

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

    @Column(name = "kill_involve_rate")
    private Double killInvolveRate;

    @Column(name = "compete_set_count")
    private Integer competeSetCount;

    @Column(name = "compete_times")
    private Integer competeTimes;

    @Column(name = "pog_point")
    private Integer pogPoint;

    @OneToOne
    @JoinColumn(name = "team_ranking_id")
    private PlayerRanking playerRanking;

    @Builder
    private PlayerAddInfo(Long id, Double kda, Integer kills, Integer deaths, Integer assists, Double killInvolveRate,
                         Integer competeSetCount, Integer competeTimes, Integer pogPoint, PlayerRanking playerRanking) {
        this.id = id;
        this.kda = kda;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.killInvolveRate = killInvolveRate;
        this.competeSetCount = competeSetCount;
        this.competeTimes = competeTimes;
        this.pogPoint = pogPoint;
        this.playerRanking = playerRanking;
    }
}
