package com.project.somsea.repository;

import com.project.somsea.domain.Nft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NftRepository extends JpaRepository<Nft, Long> {
}
