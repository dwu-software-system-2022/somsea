package com.project.somsea.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "nft")
public class Nft {

    @Id @GeneratedValue
    @Column(name = "nft_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "token")
    private String token;

    @Column(name = "contract_add")
    private String contractAdd;

    @Column(name = "token_std")
    private String tokenStd;

    @Column(name = "block_chain")
    private String blockChain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "nft", fetch = FetchType.LAZY)
    private List<NftInfo> nftInfos = new ArrayList<>();
}
