package com.project.somsea.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Part {

    @Id @GeneratedValue
    @Column(name = "part_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "part", fetch = FetchType.LAZY)
    private List<NftInfo> nftInfos;
}
