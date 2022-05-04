package com.cg.controller.restAPI;

import com.cg.model.dto.CategoryDTO;
import com.cg.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> showListCategories() {
        List<CategoryDTO> categoryDTOS = categoryService.findAllCategoriesDTO();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }
}
