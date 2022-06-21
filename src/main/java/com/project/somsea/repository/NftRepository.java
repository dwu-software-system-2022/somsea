package com.project.somsea.repository;

import com.project.somsea.domain.Collection;
import com.project.somsea.domain.Nft;
import com.project.somsea.domain.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.transaction.Transactional;

public interface NftRepository extends JpaRepository<Nft, Long> {
    List<Nft> findAllByCollection(Collection collection);

    @Query(value = "SELECT DISTINCT n FROM Nft n JOIN FETCH n.nftInfos infos WHERE infos.part IN (:parts)")
    List<Nft> findAllByJoinWithParts(@Param("parts") List<Part> parts);
    
    @Transactional
	@Modifying
	@Query("update Nft set user_id = ?1 where id = ?2")
	public void updateUserIdOfNft(Long userId, Long id);
    
//    @Query("SELECT * FROM nft WHERE user_id = ?")
    List<Nft> findAllByUserId(Long userId);    
}
