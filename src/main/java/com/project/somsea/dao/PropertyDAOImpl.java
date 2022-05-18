package com.project.somsea.dao;

import com.project.somsea.domain.Nft;
import com.project.somsea.domain.Property;

public class PropertyDAOImpl implements PropertyDAO{
    @Override
    public boolean existing(long propertyId) {
        return false;
    }

    @Override
    public long add(Property property) {
        return 0;
    }

    @Override
    public long update(Property property) {
        return 0;
    }

    @Override
    public long delete(Property property) {
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
