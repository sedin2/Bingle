package com.bingle.accountteamsubscription.model;

import com.bingle.account.model.Account;
import com.bingle.player.model.Player;
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
@Table(name = "account_team_subscriptions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTeamSubscription {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "subscriptions_date")
    private LocalDateTime subscriptionDate;

    @ManyToOne
    @JoinColumn(name = "fk_accounts_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "fk_teams_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "fk_players_id")
    private Player player;

    @Builder
    private AccountTeamSubscription(Long id, LocalDateTime subscriptionDate,
                                    Account account, Team team, Player player) {
        this.id = id;
        this.subscriptionDate = subscriptionDate;
        this.account = account;
        this.team = team;
        this.player = player;
    }
}
