package com.project.somsea.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    
    @OneToMany(mappedBy = "collection", fetch = FetchType.LAZY, orphanRemoval = true)
	private List<Nft> nfts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Collection(String name, String url, String logoImgUrl, String description, User user) {
    	this.name = name;
    	this.url = url;
    	this.logoImgUrl = logoImgUrl;
    	this.description = description;
        this.user = user;
    }
    public void updateNameAndDesc(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

