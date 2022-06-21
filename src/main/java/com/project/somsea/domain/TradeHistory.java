package com.project.somsea.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.project.somsea.domain.Auction.Status;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "amount")
    private Long amount;

    public enum Status {
        BID_SUCCESS, BID_FAIL
    }
    
    @Builder
    public TradeHistory(User user, Auction auction, Long amount, Status status) {
        this.user = user;
        this.auction = auction;
        this.amount = amount;
        this.status = status;
    }
}
