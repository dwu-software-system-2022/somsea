package com.project.somsea.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Nft {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String imageUrl;
    private String token;
    private String contractAdd;
    private String tokenStd;
    private String blockChain;
    private Long collectionId;
    @Column(name = "USER_ID")
    private Long userId;

    @OneToMany(mappedBy = "nft", fetch = FetchType.LAZY)
    private List<NftInfo> nftInfos;
}
