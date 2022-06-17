package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "wallet")
public class Wallet {

	@Id @GeneratedValue
	@Column(name = "wallet_id")
	private Long id;

	@Column(name = "balance")
	private Long balance;
	
	@Builder
	public Wallet(Long balance) {
		this.balance = balance;
	}
}
