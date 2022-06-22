package com.project.somsea.controller;

import com.project.somsea.domain.Collection;
import com.project.somsea.service.CollectionService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class CollectionController {
		@Autowired
		private final CollectionService collectionService;
		
		//새로운 컬렉션 등록, return 등록된 컬렉션 정보 
		@PostMapping
	    public ResponseEntity<Collection> save(Collection colletion) {
	        return new ResponseEntity<Collection>(collectionService.save(colletion), HttpStatus.OK);
	    }
		
		//id 이용해서 컬렉션 정보 수정 
		@PutMapping(value = "collections/{collectionId}")
	    public ResponseEntity<Collection> updateCollection(@PathVariable("id") Long id, Collection collection) {
	        collectionService.updateById(id, collection);
	        return new ResponseEntity<Collection>(collection, HttpStatus.OK);
	    }
		
		//id 이용해서 사용자 검색
		@GetMapping(value = "collections/{collectionId}")
	    public ResponseEntity<Collection> getCollection(@PathVariable("id") Long id) {
	        Optional<Collection> collection = collectionService.findById(id);
	        return new ResponseEntity<Collection>(collection.get(), HttpStatus.OK);
	    }
}