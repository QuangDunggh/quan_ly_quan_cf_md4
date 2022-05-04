package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT new com.cg.model.dto.ProductDTO(" +
            "p.idProduct, " +
            "p.nameProduct," +
            "p.price," +
            "p.category.idCategory," +
            "p.category.nameCategory," +
            "p.description)" +
            "FROM Product p " +
            "WHERE p.deleted = false ")
    List<ProductDTO> findAllProductDTO();

    @Query("SELECT new com.cg.model.dto.ProductDTO(" +
            "p.idProduct, " +
            "p.nameProduct," +
            "p.price," +
            "p.category.idCategory," +
            "p.category.nameCategory," +
            "p.description)" +
            "FROM Product p " +
            "WHERE p.deleted = false " +
            "AND p.idProduct=:id")
    Optional<ProductDTO> findProductDTOById(@Param("id") Long id);
}
