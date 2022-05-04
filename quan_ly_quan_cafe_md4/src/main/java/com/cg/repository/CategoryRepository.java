package com.cg.repository;

import com.cg.model.Category;
import com.cg.model.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT new com.cg.model.dto.CategoryDTO(" +
            "c.idCategory, " +
            "c.nameCategory) " +
            "FROM Category c " +
            "WHERE c.deleted = false ")
    List<CategoryDTO> findAllCategoriesDTO();
}
