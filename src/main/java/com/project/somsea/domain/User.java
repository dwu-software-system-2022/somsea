package com.project.somsea.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user_info")
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TradeHistory> tradeHistories = new ArrayList<>();
}
