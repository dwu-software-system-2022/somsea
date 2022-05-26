package com.project.somsea.service;

import com.project.somsea.domain.*;
import com.project.somsea.dto.NftDto;
import com.project.somsea.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NftService {

    private final NftRepository nftRepository;
    private final UserRepository userRepository;
    private final CollectionRepository collectionRepository;
    private final PartRepository partRepository;
    private final NftInfoRepository nftInfoRepository;

    public Long add(Long userId, NftDto nftDto) {
        // NFT 저장
        User user = findUser(userId);
        Collection collection = findCollection(nftDto);
        Nft nft = nftDto.toEntity(user, collection);
        nftRepository.save(nft);

        // NFT_INFO 저장
        List<NftInfo> nftInfos = nftDto.getPartIds().stream()
                                       .map(this::findPart)
                                       .map(part -> NftInfo.builder().nft(nft).part(part).build())
                                       .collect(Collectors.toList());
        nftInfoRepository.saveAll(nftInfos);

        return nft.getId();
    }

    

    private Part findPart(Long partId) {
        return partRepository.findById(partId)
                .orElseThrow(() -> new IllegalArgumentException("Part Id 값이 없습니다. PartId: " + partId));
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Id 값이 없습니다. UserId: " + userId));
    }

    private Collection findCollection(NftDto nftDto) {
        return collectionRepository.findById(nftDto.getCollectionId())
                .orElseThrow(() -> new IllegalArgumentException("Collection Id 값이 없습니다. CollectionId: " + nftDto.getCollectionId()));
    }
}
