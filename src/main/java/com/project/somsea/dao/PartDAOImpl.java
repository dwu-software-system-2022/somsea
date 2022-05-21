package com.project.somsea.dao;

import com.project.somsea.domain.Nft;
import com.project.somsea.domain.Part;

public class PartDAOImpl implements PartDAO {
    @Override
    public boolean existing(long propertyId) {
        return false;
    }

    @Override
    public long add(Part part) {
        return 0;
    }

    @Override
    public long update(Part part) {
        return 0;
    }

    @Override
    public long delete(Part part) {
        return 0;
    }

    @Override
    public long deleteAllByNft(Nft nft) {
        return 0;
    }

    @Override
    public long deleteAllByCollection() {
        return 0;
    }

    @Override
    public long find(long propertyId) {
        return 0;
    }
}
