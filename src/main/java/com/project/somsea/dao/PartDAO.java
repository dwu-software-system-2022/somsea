package com.project.somsea.dao;

import com.project.somsea.domain.Nft;
import com.project.somsea.domain.Part;


public interface PartDAO {
    boolean existing(long propertyId);
    long add(Part part);
    long update(Part part);
    long delete(Part part);
    long deleteAllByNft(Nft nft);
    //todo collectionId 넣기
    long deleteAllByCollection();
    long find(long propertyId);
}
