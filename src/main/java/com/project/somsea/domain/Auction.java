package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "auction")
public class Auction {

	@Id @GeneratedValue
	@Column(name = "auction_id")
	private Long id;

	@Column(name = "start_price")//입찰가
	private Long startPrice;

	@Column(name = "due_date")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	private LocalDateTime dueDate;

	@Column(name = "register_date")//등록날짜
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
	@CreationTimestamp
	private LocalDateTime registerDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;	// 진행 전 , 진행 중, 진행 후 

	@JoinColumn(name = "nft_id")
	@OneToOne(fetch=FetchType.LAZY, cascade = CascadeType.REMOVE) 
	private Nft nft;
	
	private Long topBid;

	public enum Status {
		READY, IN_PROGRESS, COMPLETED
	}
	
	@Builder
    public Auction(Nft nft, Long startPrice, LocalDateTime registerDate, LocalDateTime dueDate,
    		Status status, Long topBid) {
        this.nft = nft;
        this.startPrice = startPrice;
        this.registerDate = registerDate;
        this.dueDate = dueDate;
        this.status = status;
        this.topBid = topBid;
    }
}
