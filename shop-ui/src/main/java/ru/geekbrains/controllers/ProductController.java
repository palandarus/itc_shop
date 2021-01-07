package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controllers.repr.BrandRepr;
import ru.geekbrains.controllers.repr.CategoryRepr;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.service.BrandService;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;


import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;
    private CategoryService categoryService;
    private BrandService brandService;

    public ProductController(ProductService productService, CategoryService categoryService, BrandService brandService) {
        this.productService = productService;
        this.categoryService=categoryService;
        this.brandService=brandService;
    }

    @GetMapping("/products")
    public String shopProductsPage(Model model) {
        List<ProductRepr> products = productService.findAll();
        List<CategoryRepr> categories = categoryService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);

        return "products";
    }



    @GetMapping("/products/{id}")
    public String shopProductEditPage(@PathVariable Long id, Model model) {

        ProductRepr p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for edit)"));
        List<CategoryRepr> categoryList=categoryService.findAll();
        List<BrandRepr> brandList=brandService.findAll();
        model.addAttribute("product", p);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        return "product_info_form";
    }





}
