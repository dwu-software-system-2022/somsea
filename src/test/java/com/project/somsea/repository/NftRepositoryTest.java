package com.project.somsea.repository;

import com.project.somsea.domain.Nft;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@SpringBootTest
class NftRepositoryTest {

    @Autowired
    private NftRepository nftRepository;

    @Test
    @DisplayName("NFT 저장 테스트")
    void testSaveNft() {
        Nft nft = new Nft();
        nftRepository.saveAndFlush(nft);
        Optional<Nft> savedNft = nftRepository.findById(nft.getId());
        Assertions.assertThat(savedNft.isPresent()).isTrue();
    }
}

/**
 * 1. 컬렉션에 들어가면 나오는 nft들 띄우기 (특정 컬렉션에 속해있는 nft list 반환)
 * 2. parts (여러개 가능) 로 필터링된 nft list 반환
 * 3. nft 등록
 * 4. nft detail 조회(bidding log랑 입찰 연결)
 * 5. nft 삭제
 * 6. nft 수정(경매 기간 수정/Auction, parts 수정)
 *
 */