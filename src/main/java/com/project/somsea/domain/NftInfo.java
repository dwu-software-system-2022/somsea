package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "nft_info")
@NoArgsConstructor
public class NftInfo {

    @Id @GeneratedValue
    @Column(name = "nft_info_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nft_id")
    private Nft nft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "part_id")
    private Part part;

    @Builder
    public NftInfo(Nft nft, Part part) {
        this.nft = nft;
        this.part = part;
        nft.getNftInfos().add(this);
        part.getNftInfos().add(this);
    }
}