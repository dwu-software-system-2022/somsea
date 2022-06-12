package main.java.com.project.somsea.controller;

import main.java.com.project.somsea.dto.CollectionDto;
import main.java.com.project.somsea.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CollectionController {
    @autowired
    private final CollectionService collectionService;
    
    @autowired
    private final CollectionRepository collectionRepository;

    @GetMapping("/collection")
    public List<Collection> retrieveAllCollection() {
        return collectionRepository.findAll();
    }
}
