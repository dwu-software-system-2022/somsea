package com.project.somsea.controller;

import com.project.somsea.dto.CategoryDto;
import com.project.somsea.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    @autowired
    private final CategoryService categoryService;
    @autowired
    private final CategoryRepository categoryRepository;

    @GetMapping("/category")
    public List<User> retrieveAllCategory() {
        return categoryRepository.findAll();
    }
}
