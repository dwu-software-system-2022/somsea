package com.project.somsea.service;

import com.project.somsea.domain.*;
import com.project.somsea.repository.*;

import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CollectionService {
	private final CollectionRepository collectionRepository;
	
	public Optional<Collection> findById(Long id) {
        Optional<Collection> collection = collectionRepository.findById(id);
        return collection;
    }
	
	public Optional<Collection> findByName(String name) {
        Optional<Collection> collection = collectionRepository.findByName(name);
        return collection;
    }
	
	public void deleteById(Long id) {
		collectionRepository.deleteById(id);
    }
	
    public Collection save(Collection collection) {
    	collectionRepository.save(collection);
        return collection;
    }
    
    public void updateById(Long id, Collection updatedCollection) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.isPresent()) {
        	collection.get().setName(updatedCollection.getName());
        	collectionRepository.save(collection.get());
        }
    }
}
