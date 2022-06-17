package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "bidding")
public class Bidding implements Comparable<Bidding>{

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
	
	@Transient
	private String floorDifference; 
	@Transient
	private int expiration;
	@Transient
	private Long FloorBid;
	
	@Builder
	public Bidding(User user, Auction auction, Long price, LocalDateTime time) {
		this.user = user;
		this.auction = auction;
		this.price = price;
		this.time = time;
	}

	@Override
	public int compareTo(Bidding o) {
		// TODO Auto-generated method stub
		if (o.price < price) {            
			return 1;        
		} else if (o.price > price) {            	
			return -1;       
		}        
		return 0;
	}
}