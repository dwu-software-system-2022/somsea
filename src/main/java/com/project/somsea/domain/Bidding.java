package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

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
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime time;

	@JoinColumn(name = "user_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;

	@JoinColumn(name = "auction_id")
	@ManyToOne(fetch = FetchType.LAZY)
	private Auction auction;
	
	private String floorDifference; 
	@Nullable
	private String expiration;
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