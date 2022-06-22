package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
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

    @Builder
    public Tag(Collection collection, Category category) {
        this.collection = collection;
        this.category = category;
        collection.getTags().add(this);
        category.getTags().add(this);
    }
}

