package com.cg.repository;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT " +
            "p.idProduct, " +
            "p.nameProduct," +
            "p.price," +
            "p.category.idCategory," +
            "p.category.nameCategory," +
            "p.description," +
            "p.imageProduct.cloudId," +
            "p.imageProduct.fileFolder," +
            "p.imageProduct.fileName," +
            "p.imageProduct.fileUrl " +
            "FROM Product p " +
            "WHERE p.deleted = false ")
    List<ProductDTO> findAllProductDTO();

    @Query("SELECT " +
            "p.idProduct, " +
            "p.nameProduct," +
            "p.price," +
            "p.category.idCategory," +
            "p.category.nameCategory," +
            "p.description," +
            "p.imageProduct.cloudId," +
            "p.imageProduct.fileFolder," +
            "p.imageProduct.fileName," +
            "p.imageProduct.fileUrl " +
            "FROM Product p " +
            "WHERE p.deleted = true ")
    List<ProductDTO> findAllProductLock();

    @Query("SELECT " +
            "p.idProduct, " +
            "p.nameProduct," +
            "p.price," +
            "p.category.idCategory," +
            "p.category.nameCategory," +
            "p.description," +
            "p.imageProduct.cloudId," +
            "p.imageProduct.fileFolder," +
            "p.imageProduct.fileName," +
            "p.imageProduct.fileUrl " +
            "FROM Product p " +
            "WHERE p.deleted = false AND p.idProduct =: id")
    Optional<ProductDTO> findProductDTOById(@Param("id") Long id);
}
