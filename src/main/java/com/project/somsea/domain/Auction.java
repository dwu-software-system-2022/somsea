package com.project.somsea.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "auction")
public class Auction {

	@Id @GeneratedValue
	@Column(name = "auction_id")
	private Long id;

	@Column(name = "start_price")//입찰가
	private Long startPrice;

	@Column(name = "due_date")
	private LocalDateTime dueDate;

	@Column(name = "register_date")//등록날짜
	private LocalDateTime registerDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;	// 진행 전 , 진행 중, 진행 후

	@JoinColumn(name = "nft_id")
	@OneToOne(fetch = FetchType.LAZY)
	private Nft nft;

	public enum Status {
		READY, IN_PROGRESS, COMPLETED
	}
}
