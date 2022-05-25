package com.project.somsea.service;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.NftInfo;
import com.project.somsea.domain.Part;
import com.project.somsea.dto.NftDto;
import com.project.somsea.repository.CollectionRepository;
import com.project.somsea.repository.NftInfoRepository;
import com.project.somsea.repository.NftRepository;
import com.project.somsea.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NftService {

    private final NftRepository nftRepository;
    private final CollectionRepository collectionRepository;
    private final PartRepository partRepository;
    private final NftInfoRepository nftInfoRepository;

    public Long add(NftDto nftDto) {
        // NFT 저장
        Collection collection = collectionRepository.findById(nftDto.getCollectionId())
                                                    .orElseThrow(() -> new IllegalArgumentException("Collection Id 값이 없습니다. CollectionId: " + nftDto.getCollectionId()));
        Nft nft = Nft.builder()
                     .imageUrl(nftDto.getImageUrl())
                     .collection(collection)
                     .build();
    
        nftRepository.save(nft);

        // NFT_INFO 저장
        Part part = partRepository.findById(nftDto.getPartIds().get(0))
                                  .orElseThrow(() -> new IllegalArgumentException("Part Id 값이 없습니다. PartId: " + nftDto.getPartIds().get(0)));
        NftInfo nftInfo = new NftInfo();
        nftInfo.setNft(nft);
        nft.getNftInfos().add(nftInfo);

        nftInfo.setPart(part);
        part.getNftInfos().add(nftInfo);

        nftInfoRepository.save(nftInfo);

        return nft.getId();
    }
}
