package com.project.somsea.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "bidding")
public class Bidding {

	@Id @GeneratedValue
	@Column(name = "bidding_id")
	private Long id;

	@Column(name = "price")
	private Long price;

	@Column(name = "time")
	private LocalDateTime time;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "auction_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Auction auction;
}