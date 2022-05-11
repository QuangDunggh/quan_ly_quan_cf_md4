package com.cg.controller;

import com.cg.model.Product;
import com.cg.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/")
    public ModelAndView showHome() {
        ModelAndView modelAndView = new ModelAndView("/product/list-product");
//        List<Product> products = productService.findAll();
//        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/listProductLock")
    public ModelAndView showProductLock() {
        ModelAndView modelAndView = new ModelAndView("/product/list-productLock");
        return modelAndView;
    }

    @GetMapping("/listCategories")
    public ModelAndView showListCategories() {
        ModelAndView modelAndView = new ModelAndView("/category/list-category");
        return modelAndView;
    }
}
