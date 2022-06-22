package com.project.somsea.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tag")
public class Tag {
    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cagegory_id")
    private Category category;
}

