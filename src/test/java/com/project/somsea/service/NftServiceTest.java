package com.project.somsea.service;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.NftInfo;
import com.project.somsea.domain.Part;
import com.project.somsea.dto.NftDto;
import com.project.somsea.repository.CollectionRepository;
import com.project.somsea.repository.NftRepository;
import com.project.somsea.repository.PartRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class NftServiceTest {

    @Autowired
    private NftService nftService;

    @Autowired
    private NftRepository nftRepository;

    @Autowired
    private CollectionRepository collectionRepository;

    @Autowired
    private PartRepository partRepository;

    @Test
    @DisplayName("NFT 추가")
    void testAddNft() {
        // given: Collection, Part 가 있어야함
        Collection collection = new Collection();
        collectionRepository.saveAndFlush(collection);

        Part part = new Part();
        partRepository.saveAndFlush(part);

        NftDto nftDto = NftDto.builder()
                              .collectionId(collection.getId())
                              .imageUrl("myImageUrl")
                              .partIds(List.of(part.getId()))
                              .build();

        // when
        Long savedNftId = nftService.add(nftDto);

        // then
        Optional<Nft> optionalNft = nftRepository.findById(savedNftId);
        assertThat(optionalNft).isNotEmpty();

        Nft nft = optionalNft.get();
        assertThat(nft.getImageUrl()).isEqualTo(nftDto.getImageUrl());

        List<Long> partIds = nft.getNftInfos().stream()
                                              .map(NftInfo::getPart)
                                              .map(Part::getId)
                                              .collect(Collectors.toList());
        assertThat(partIds).isEqualTo(nftDto.getPartIds());
    }
}






