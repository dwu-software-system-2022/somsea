package com.project.somsea.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Part {

    @Id @GeneratedValue
    @Column(name = "part_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "part", fetch = FetchType.LAZY)
    private List<NftInfo> nftInfos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;
}
