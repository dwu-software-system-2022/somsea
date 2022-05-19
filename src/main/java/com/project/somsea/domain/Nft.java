package com.project.somsea.domain;

import javax.persistence.*;

@Entity
public class Nft {
    @Id @GeneratedValue
    private Long id;
    private String title;
    private String imageUrl;
    private String token;
    private String contractAdd;
    private String tokenStd;
    private String blockChain;
    private Long collectionId;
    @Column(name = "USER_ID")
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getContractAdd() {
        return contractAdd;
    }

    public void setContractAdd(String contractAdd) {
        this.contractAdd = contractAdd;
    }

    public String getTokenStd() {
        return tokenStd;
    }

    public void setTokenStd(String tokenStd) {
        this.tokenStd = tokenStd;
    }

    public String getBlockChain() {
        return blockChain;
    }

    public void setBlockChain(String blockChain) {
        this.blockChain = blockChain;
    }

    public Long getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(Long collectionId) {
        this.collectionId = collectionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
