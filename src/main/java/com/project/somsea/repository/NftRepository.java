package com.project.somsea.repository;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NftRepository extends JpaRepository<Nft, Long> {
    List<Nft> findAllByCollection(Collection collection);
}
