package com.project.somsea.service;

import com.project.somsea.domain.*;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.PartDto;
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

    public Long add(Long userId, NftDto.Request nftDto) {
        // NFT 저장
        User user = findUser(userId);
        Collection collection = findCollection(nftDto.getCollectionId());
        Nft nft = nftDto.toEntity(user, collection);
        nftRepository.save(nft);

        // NFT_INFO 저장
        if (nftDto.getPartIds() != null) {
            List<NftInfo> nftInfos = nftDto.getPartIds().stream()
                    .map(this::findPart)
                    .map(part -> NftInfo.builder().nft(nft).part(part).build())
                    .collect(Collectors.toList());
            nftInfoRepository.saveAll(nftInfos);
        }

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

    public void delete(Long userId, Long nftId) {
        // DB 에 존재하는 NFT 조회
        Nft nft = findNft(nftId);

        // NFT Validate: UserId 가 맞는지
        User user = nft.getUser();

        if (user.isNotEquals(userId)) {
            throw new IllegalArgumentException("userId 값이 다릅니다.");
        }

        // NFT 삭제
        List<NftInfo> nftInfos = nft.getNftInfos(); // List<ProxyNftInfo> : Id 값만 갖고있는 프록시 객체들의 리스트
        nftInfoRepository.deleteAll(nftInfos);  // 삭제할 때는 id 값만 필요함
        nftRepository.delete(nft);
    }

    private Nft findNft(Long nftId) {
        return nftRepository.findById(nftId)
                .orElseThrow(() -> new IllegalArgumentException("Nft Id 값이 없습니다. NftId: " + nftId));
    }

    /**
     * SELECT * FROM nft WHERE nft.collection_id = ?
     */
    public List<NftDto.Response> readNftByCollection(Long collectionId) {
        Collection collection = findCollection(collectionId);

        return nftRepository.findAllByCollection(collection).stream()
                .map(NftDto.Response::of)
                .collect(Collectors.toList());
    }

    private Collection findCollection(Long collectionId) {
        return collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection Id 값이 없습니다. collectionId: " + collectionId));
    }

    public NftDto.ResponseDetail readDetailNft(Long nftId) {
        Nft nft = findNft(nftId);
        return NftDto.ResponseDetail.of(nft);
    }

    public NftDto.Request readNftForUpdate(Long nftId) {
        Nft nft = findNft(nftId);
        return NftDto.Request.of(nft);
    }

    public List<NftDto.Response> readNftByParts(List<Long> partIds) {
        List<Part> parts = partRepository.findAllById(partIds);
        List<Long> nftIds = nftRepository.findNftIdsByParts(parts, parts.size());

        return nftRepository.findAllById(nftIds).stream()
                .map(NftDto.Response::of)
                .collect(Collectors.toList());
    }

    /**
     * 임시로 만드는 Parts 조회 메서드
     * TODO: CollectionService 또는 PartService 로 나중에 옮겨야함
     */
    public List<PartDto.Response> getPartsByCollectionId(Long collectionId) {
        return findCollection(collectionId)
                .getParts().stream()
                .map(PartDto.Response::of)
                .collect(Collectors.toList());
    }

    public List<PartDto.Response> getPartsByNftId(Long nftId) {
        return findNft(nftId)
                .getNftInfos().stream()
                .map(NftInfo::getPart)
                .map(PartDto.Response::of)
                .collect(Collectors.toList());
    }
    
    
    /**
     *  경매에 낙찰된 경우 nft의 user_id를 낙찰자의 id로 변경해주어야 함.
     */
    public void updateUserIdOfNft(Long userId, Long nftId) {
    	User user =findUser(userId);
    	Nft nft = findNft(nftId);
        nft.changeUser(user);
    }
    
    public List<NftDto.Response> readNftsByUserId(Long userId) {
        return nftRepository.findAllByUser(findUser(userId)).stream()
                .map(NftDto.Response::of)
                .collect(Collectors.toList());
    }

    public void update(Long userId, Long nftId, NftDto.Request nftDto) {
        Nft nft = findNft(nftId);

        // NFT Validate: UserId 가 맞는지
        User user = nft.getUser();

        if (user.isNotEquals(userId)) {
            throw new IllegalArgumentException("userId 값이 다릅니다.");
        }

        nft.updateTitleAndDesc(nftDto.getTitle(), nftDto.getDesc());
    }
}
