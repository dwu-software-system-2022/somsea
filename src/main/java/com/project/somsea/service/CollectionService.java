package com.project.somsea.service;

import com.project.somsea.domain.*;
import com.project.somsea.dto.CategoryDto;
import com.project.somsea.dto.CollectionDto;
import com.project.somsea.dto.NftDto;
import com.project.somsea.dto.PartDto;
import com.project.somsea.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {
	private final CollectionRepository collectionRepository;
    private final UserRepository userRepository;
    private final PartRepository partRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final NftRepository nftRepository;


    public List<CollectionDto.Response> findAll(){
        return collectionRepository.findAll().stream()
                .map(CollectionDto.Response::of)
                .collect(Collectors.toList());
    }

    public List<CollectionDto.Response> findByCategoryId(Long categoryId) {
        return findCategory(categoryId).getTags().stream()
                .map(Tag::getCollection)
                .map(CollectionDto.Response::of)
                .collect(Collectors.toList());
    }

    public Long add(Long userId, CollectionDto.Request collectionDto) {
        User user = findUser(userId);
        Collection collection = collectionDto.toEntity(user);

        //Category 선택
        List<Tag> tags = collectionDto.getCategoryIds().stream()
                    .map(this::findCategory)
                    .map(category -> Tag.builder().collection(collection).category(category).build())
                    .collect(Collectors.toList());
        tagRepository.saveAll(tags);

        // Part 추가
        List<Part> parts = collectionDto.generatePartEntities(collection);
        partRepository.saveAll(parts);

        collectionRepository.save(collection);
        return collection.getId();
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User Id 값이 없습니다. UserId: " + userId));
    }

    private Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category Id 값이 없습니다. CategoryId: " + categoryId));
    }

    private Collection findCollection(Long collectionId) {
        return collectionRepository.findById(collectionId)
                .orElseThrow(() -> new IllegalArgumentException("Collection Id 값이 없습니다. collectionId: " + collectionId));
    }
    public List<CollectionDto.Response> readCollectionsByUserId(Long userId) {
        return collectionRepository.findAllByUser(findUser(userId)).stream()
                .map(CollectionDto.Response::of)
                .collect(Collectors.toList());
    }

    public CollectionDto.Request readCollectionForUpdate(Long collectionId) {
        Collection collection = findCollection(collectionId);
        return CollectionDto.Request.of(collection);
    }

    public List<PartDto.Response> getPartsByCollectionId(Long collectionId) {
        return findCollection(collectionId)
                .getParts().stream()
                .map(PartDto.Response::of)
                .collect(Collectors.toList());
    }

    public void update(Long userId, Long collectionId, CollectionDto.Request collectionDto) {
        Collection collection = findCollection(collectionId);

        // NFT Validate: UserId 가 맞는지
        User user = collection.getUser();

        if (user.isNotEquals(userId)) {
            throw new IllegalArgumentException("userId 값이 다릅니다.");
        }
        collection.updateNameAndDesc(collectionDto.getName(), collectionDto.getDescription());
    }

    public void delete(Long userId, Long collectionId) {
        // DB 에 존재하는 NFT 조회
        Collection collection = findCollection(collectionId);

        // NFT Validate: UserId 가 맞는지
        User user = collection.getUser();

        if (user.isNotEquals(userId)) {
            throw new IllegalArgumentException("userId 값이 다릅니다.");
        }
        collectionRepository.delete(collection);
    }
}
