package com.cg.controller.restAPI;

import com.cg.model.Product;
import com.cg.model.dto.ProductDTO;
import com.cg.service.category.ICategoryService;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ICategoryService categoryService;

//    @Autowired
//    private AppUtils appUtils;


    @GetMapping("/")
    public ResponseEntity<?> findAll() {
        List<ProductDTO> productDTOS = productService.findAllProductDTO();

        if(productDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(productDTOS,HttpStatus.OK);
    }

    @GetMapping("/lockProduct")
    public ResponseEntity<?> findAllLockProduct() {
        List<ProductDTO> productDTOS = productService.findAllProductLock();
        if(productDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productDTOS,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        Optional<ProductDTO> productDTO = productService.findProductDTOById(id);

        if(productDTO.isPresent()) {
            return new ResponseEntity<>(productDTO.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Can not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create",consumes = {"application/json"})
    public ResponseEntity<?> save(@Validated @RequestBody ProductDTO productDTO,
                                  BindingResult result) {
        if(result.hasFieldErrors()) {
//            return appUtils.mapErrorToResponse(result);
            System.out.println("lỗi");
        }
//        productDTO.setId(0);
        Product product = new Product();
        product.setNameProduct(productDTO.getNameProduct());
        product.setPrice(productDTO.getPrice());
        product.setCategory(categoryService.findById(productDTO.getCategory()).get());
        product.setDescription(productDTO.getDescription());
        System.out.println("vao day chua");
        productService.save(product);
        productDTO.setId(product.getIdProduct());
        System.out.println(productDTO);
        return new ResponseEntity<>(productDTO,HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> update(@PathVariable("id") Long id,
                                     @RequestBody ProductDTO productDTO) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()) {
            product.get().setNameProduct(productDTO.getNameProduct());
            product.get().setPrice(productDTO.getPrice());
            product.get().setDescription(productDTO.getDescription());
            productService.save(product.get());
            return new ResponseEntity<>(productDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found product", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> suspension(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()) {
            product.get().setDeleted(true);
            productService.save(product.get());
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.get().getIdProduct());
            return new ResponseEntity<>(productDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>("Not found product", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/unSuspension/{id}")
    public ResponseEntity<?> unSuspension(@PathVariable("id") Long id) {
        Optional<Product> product = productService.findById(id);
        if(product.isPresent()) {
            product.get().setDeleted(false);
            productService.save(product.get());
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.get().getIdProduct());
            productDTO.setNameProduct(product.get().getNameProduct());
            productDTO.setPrice(product.get().getPrice());
            productDTO.setDescription(product.get().getDescription());
            return new ResponseEntity<>(productDTO,HttpStatus.OK);

        }
        return new ResponseEntity<>("Can not find product",HttpStatus.NOT_FOUND);
    }
}
