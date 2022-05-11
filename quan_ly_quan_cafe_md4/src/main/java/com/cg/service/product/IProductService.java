package com.cg.service.product;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {

    List<ProductDTO> findAllProductDTO();

    List<ProductDTO> findAllProductLock();

    Optional<ProductDTO> findProductDTOById(Long id);
}
