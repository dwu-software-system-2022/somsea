package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Part {

    @Id @GeneratedValue
    @Column(name = "part_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "part", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<NftInfo> nftInfos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @Builder
    public Part(String name, Collection collection) {
        this.name = name;
        this.collection = collection;
        collection.getParts().add(this);
    }
}
