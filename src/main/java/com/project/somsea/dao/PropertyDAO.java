package com.project.somsea.dao;

import com.project.somsea.domain.Nft;
import com.project.somsea.domain.Property;


public interface PropertyDAO {
    boolean existing(long propertyId);
    long add(Property property);
    long update(Property property);
    long delete(Property property);
    long deleteAllByNft(Nft nft);
    //todo collectionId 넣기
    long deleteAllByCollection();
    long find(long propertyId);
}
