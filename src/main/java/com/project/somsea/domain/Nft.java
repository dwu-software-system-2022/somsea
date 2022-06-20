package com.project.somsea.domain;

import com.project.somsea.util.TokenGenerator;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "nft")
@NoArgsConstructor
public class Nft {

    @Id @GeneratedValue
    @Column(name = "nft_id")
    private Long id;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "token")
    private String token;

    @Column(name = "token_standard")
    @Enumerated(EnumType.STRING)
    private TokenStandard tokenStandard;

    @Column(name = "block_chain")
    @Enumerated(EnumType.STRING)
    private BlockChain blockChain;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "nft", fetch = FetchType.LAZY)
    private List<NftInfo> nftInfos = new ArrayList<>();

    public enum BlockChain {
       ETHEREUM, TRON, KLAYTN
    }

    public enum TokenStandard{
        ERC_721, TRC_721, KIP_17
    }

    @Builder
    public Nft(String imageUrl, Collection collection, User user) {
        this.imageUrl = imageUrl;
        this.collection = collection;
        this.user = user;
        this.blockChain = BlockChain.ETHEREUM;
        this.tokenStandard = TokenStandard.ERC_721;
        this.token = TokenGenerator.randomCharacterWithPrefix("nft_token_");
    }

    public String getTitle(){
        return collection.getName() + id;
    }

}
