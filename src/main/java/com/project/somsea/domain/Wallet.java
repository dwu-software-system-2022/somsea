package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wallet")
public class Wallet {

	@Id @GeneratedValue
	@Column(name = "wallet_id")
	private Long id;

	@Column(name = "balance")
	private Long balance;
	
	@JoinColumn(name = "user_id")
 	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
 	private User user;
	
	@Builder
	public Wallet(User user, Long balance) {
		this.user = user;
		this.balance = balance;
	}
}
