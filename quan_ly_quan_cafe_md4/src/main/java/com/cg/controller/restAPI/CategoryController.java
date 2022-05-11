package com.cg.controller.restAPI;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import com.cg.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> showListCategories() {
        List<CategoryDTO> categoryDTOS = categoryService.findAllCategoriesDTO();
        if(categoryDTOS.isEmpty()) {
            return new ResponseEntity<>("Category not exist", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @GetMapping("/findCategoryById/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable("id") Long id) {
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()) {
            CategoryDTO categoryDTO = categoryService.findCategoryDTOById(id);
            return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found category", HttpStatus.NOT_FOUND);

    }
}
