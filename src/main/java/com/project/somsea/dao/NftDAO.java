package com.project.somsea.dao;

import com.project.somsea.domain.Nft;

public interface NftDAO {
    boolean existing(long nftId);
    long add(Nft nft);
    long update(Nft nft);
    long delete(Nft nft);
    long deleteAllByCollectionId(long collectionId);
    long find(long nftId);
}
