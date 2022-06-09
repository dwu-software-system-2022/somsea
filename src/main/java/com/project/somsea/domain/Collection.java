package com.project.somsea.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "collection")
public class Collection {

    @Id @GeneratedValue
    @Column(name = "collection_id")
    private Long id;

    @Column(name = "collection_name")
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "logo_img_url")
    private String logoImgUrl;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY)
    private List<Part> parts = new ArrayList<>();
}

