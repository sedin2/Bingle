package com.bingle.player.model;

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

@Entity
@Getter
@Table(name = "players")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Player {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "player_id")
    private Long playerId;

    @Column(name = "team_id")
    private String teamId;

    @Column(name = "game_code")
    private String gameCode;

    @Column(name = "name")
    private String name;

    @Column(name = "name_eng")
    private String nameEng;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "fk_team_id")
    private Team team;

    @Builder
    private Player(Long id, Long playerId, String teamId, String gameCode,
                   String name, String nameEng, String nickname, String imageUrl, Team team) {
        this.id = id;
        this.playerId = playerId;
        this.teamId = teamId;
        this.gameCode = gameCode;
        this.name = name;
        this.nameEng = nameEng;
        this.nickname = nickname;
        this.imageUrl = imageUrl;
        this.team = team;
    }
}
