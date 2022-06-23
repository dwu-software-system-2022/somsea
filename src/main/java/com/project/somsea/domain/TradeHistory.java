package com.project.somsea.domain;


import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
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

    @Column(name = "amount")
    private Long amount;

    @Builder
    public TradeHistory(User user, Auction auction, Long amount) {
        this.user = user;
        this.auction = auction;
        this.amount = amount;
    }
}
