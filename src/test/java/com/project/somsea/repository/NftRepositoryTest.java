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