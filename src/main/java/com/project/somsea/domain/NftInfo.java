package com.project.somsea.domain;

import javax.persistence.*;

@Entity
@Table(name = "nft_info")
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
}