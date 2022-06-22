package com.project.somsea.repository;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.Part;
import com.project.somsea.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NftRepository extends JpaRepository<Nft, Long> {
    List<Nft> findAllByCollection(Collection collection);

    @Query(nativeQuery = true,
            value = "SELECT nft_id FROM NFT_INFO WHERE part_id IN (:parts) GROUP BY nft_id HAVING COUNT(*) = :partSize")
    List<Long> findNftIdsByParts(@Param("parts") List<Part> parts, @Param("partSize") Integer partSize);

    List<Nft> findAllByUser(User user);
}
