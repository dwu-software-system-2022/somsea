package main.java.com.project.somsea.service;

import java.util.List;
import java.util.stream.Collectors;

import main.java.com.project.somsea.domain.*;
import main.java.com.project.somsea.dto.CollectionDto;
import main.java.com.project.somsea.repository.*;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {
	private final CollectionRepository collectionRepository;
	private final NftRepository nftRepository;

	private Collection findCollectionById(Long id) {
		return collectionRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Id value don't exist."));
	}
	private Collection findCollectionByName(String name) {
		return collectionRepository.findByName(name)
				.orElseThrow(() -> new IllegalArgumentException("Name value don't exist."));
	}
	private Collection findAllCollection() {
		return collectionRepository.findAll();
	}
	
	public Long add(Nft nft) {
		nftRepository.save(nftId);
		return nft.getId();
	}
}
