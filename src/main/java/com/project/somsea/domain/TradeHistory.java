package com.project.somsea.domain;

import javax.persistence.*;

@Entity
@Table(name = "trade_history")
public class TradeHistory {

    @Id @GeneratedValue
    @Column(name = "trade_history_id")
    private Long id;

    @JoinColumn(name = "auction_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Auction auction;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "amount")
    private Long amount;

    enum Status {
        BID_SUCCESS, BID_FAIL
    }
}
