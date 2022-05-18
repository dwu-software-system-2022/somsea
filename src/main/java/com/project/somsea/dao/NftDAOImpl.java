package com.project.somsea.dao;

import com.project.somsea.domain.Nft;

public class NftDAOImpl implements NftDAO{
    @Override
    public boolean existing(long nftId) {
        return false;
    }

    @Override
    public long add(Nft nft) {
        return 0;
    }

    @Override
    public long update(Nft nft) {
        return 0;
    }

    @Override
    public long delete(Nft nft) {
        return 0;
    }

    @Override
    public long deleteAllByCollectionId(long collectionId) {
        return 0;
    }

    @Override
    public long find(long nftId) {
        return 0;
    }
}
