package com.bingle.account.model;

import lombok.AccessLevel;
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
@Table(name = "notifications")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue
    @Column(name = "notifications_id")
    private Long id;

    @Column(name = "is_schedule_notification")
    private Boolean isScheduleNotification;

    @Column(name = "is_live_match_link")
    private Boolean isLiveMatchLink;

    @Column(name = "is_match_highlight")
    private Boolean isMatchHighlight;

    @Column(name = "is_match_weekly_report")
    private Boolean isMatchWeeklyReport;

    @Column(name = "is_spoiler")
    private Boolean isSpoiler;

    @OneToOne
    @JoinColumn(name = "fk_accounts_id")
    private Account account;

    public Notification(Long id, Boolean isScheduleNotification, Boolean isLiveMatchLink, Boolean isMatchHighlight,
                        Boolean isMatchWeeklyReport, Boolean isSpoiler, Account account) {
        this.id = id;
        this.isScheduleNotification = isScheduleNotification;
        this.isLiveMatchLink = isLiveMatchLink;
        this.isMatchHighlight = isMatchHighlight;
        this.isMatchWeeklyReport = isMatchWeeklyReport;
        this.isSpoiler = isSpoiler;
        this.account = account;
    }
}
