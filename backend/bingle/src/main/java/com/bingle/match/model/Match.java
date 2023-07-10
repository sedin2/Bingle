package com.bingle.match.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "match")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Match {


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "game_id")
    private String gameId;

    @Column(name = "league_id")
    private String leagueId;

    @Column(name = "top_league_id")
    private String topLeagueId;

    @Column(name = "game_code")
    private String gameCode;

    @Column(name = "stadium")
    private String stadium;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "title")
    private String title;

    @Column(name = "home_score")
    private Integer homeScore;

    @Column(name = "away_score")
    private Integer awayScore;

    @Column(name = "weeks")
    private Integer weeks;

    @Column(name = "days")
    private Integer days;

    @Column(name = "winner")
    private String winner;

    @Column(name = "match_status")
    private String matchStatus;

    @Column(name = "max_match_count")
    private Integer maxMatchCount;

    @Column(name = "current_match_set")
    private Integer currentMatchSet;

    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @Builder
    private Match(Long id, String gameId, String leagueId, String topLeagueId, String gameCode,
                  String stadium, LocalDateTime startDate, String title, Integer homeScore,
                  Integer awayScore, Integer weeks, Integer days, String winner, String matchStatus,
                  Integer maxMatchCount, Integer currentMatchSet, Team homeTeam, Team awayTeam) {
        this.id = id;
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
}
