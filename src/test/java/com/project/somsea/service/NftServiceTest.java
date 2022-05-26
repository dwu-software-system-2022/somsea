package com.project.somsea.service;

import com.project.somsea.domain.*;
import com.project.somsea.dto.NftDto;
import com.project.somsea.repository.*;
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

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NftInfoRepository nftInfoRepository;

    @Test
    @DisplayName("NFT 추가")
    void testAddNft() {
        // given: User, Collection, Part 가 있어야함
        User user = new User();
        userRepository.saveAndFlush(user);

        Collection collection = new Collection();
        collectionRepository.saveAndFlush(collection);

        Part part1 = new Part();
        Part part2 = new Part();
        partRepository.saveAndFlush(part1);
        partRepository.saveAndFlush(part2);

        NftDto.Request nftDto = NftDto.Request.builder()
                              .collectionId(collection.getId())
                              .imageUrl("myImageUrl")
                              .partIds(List.of(part1.getId(), part2.getId()))
                              .build();

        // when
        Long savedNftId = nftService.add(user.getId(), nftDto);

        // then
        Optional<Nft> optionalNft = nftRepository.findById(savedNftId);
        assertThat(optionalNft).isNotEmpty();

        Nft nft = optionalNft.get();
        assertThat(nft.getImageUrl()).isEqualTo(nftDto.getImageUrl());
        assertThat(nft.getUser()).isEqualTo(user);
        assertThat(nft.getCollection()).isEqualTo(collection);

        List<Long> partIds = nft.getNftInfos().stream()
                                              .map(NftInfo::getPart)
                                              .map(Part::getId)
                                              .collect(Collectors.toList());
        assertThat(partIds).isEqualTo(nftDto.getPartIds());
    }

    @Test
    @DisplayName("Nft 삭제")
    void testDeleteNft() {
        //given : Nft, Use, NftInfo 가 있어야함
        User user = new User();
        userRepository.saveAndFlush(user);

        Nft nft = Nft.builder().user(user).build();
        nftRepository.saveAndFlush(nft);

        Part part = new Part();
        partRepository.saveAndFlush(part);

        NftInfo nftInfo = NftInfo.builder().nft(nft).part(part).build();
        nftInfoRepository.saveAndFlush(nftInfo);

        //when
        nftService.delete(user.getId(), nft.getId());

        //then
        nftRepository.flush();
        Optional<Nft> optionalNft = nftRepository.findById(nft.getId());
        assertThat(optionalNft).isEmpty();

        nftInfoRepository.flush();
        Optional<NftInfo> optionalNftInfo = nftInfoRepository.findById(nft.getId());
        assertThat(optionalNftInfo).isEmpty();
    }

    @Test
    @DisplayName("특정 컬렉션에 속해있는 Nft List (UserId, NftId, ImageUrl, NftTitle) 반환")
    void testReadNftByCollection() {
        //given : Nft 2개 이상, Collection, User 2개  이상
        Collection collection = new Collection();
        collectionRepository.saveAndFlush(collection);

        User user1 = new User();
        userRepository.saveAndFlush(user1);
        User user2 = new User();
        userRepository.saveAndFlush(user2);

        Nft nft1 = Nft.builder().imageUrl("myImageUrl1").collection(collection).user(user1).build();
        nftRepository.saveAndFlush(nft1);
        Nft nft2 = Nft.builder().imageUrl("myImageUrl2").collection(collection).user(user2).build();
        nftRepository.saveAndFlush(nft2);

        //when
        List<NftDto.ResponseByCollection> nftDtos = nftService.readNftByCollection(collection.getId());

        //then
        NftDto.ResponseByCollection nftDto1 = nftDtos.get(0);
        assertThat(nftDto1.getUserId()).isEqualTo(user1.getId());
        assertThat(nftDto1.getNfTId()).isEqualTo(nft1.getId());
        assertThat(nftDto1.getImageUrl()).isEqualTo(nft1.getImageUrl());
        assertThat(nftDto1.getNftTitle()).isEqualTo(nft1.getTitle());

        NftDto.ResponseByCollection nftDto2 = nftDtos.get(1);
        assertThat(nftDto2.getUserId()).isEqualTo(user2.getId());
        assertThat(nftDto2.getNfTId()).isEqualTo(nft2.getId());
        assertThat(nftDto2.getImageUrl()).isEqualTo(nft2.getImageUrl());
        assertThat(nftDto2.getNftTitle()).isEqualTo(nft2.getTitle());
    }
    @Test
    @DisplayName("Nft 디테일 조회")
    void testReadDetailNft() {
        //given


        //when


        //then


    }

    @Test
    @DisplayName("파츠로 Nft 조회")
    void testReadNftByParts() {
        //given



        //when




        //then


    }
}






