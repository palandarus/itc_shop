package ru.geekbrains.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.geekbrains.controllers.repr.BrandRepr;
import ru.geekbrains.controllers.repr.CategoryRepr;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.service.BrandService;
import ru.geekbrains.service.CartService;
import ru.geekbrains.service.CategoryService;
import ru.geekbrains.service.ProductService;

import java.util.List;

@Controller
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;
    private CategoryService categoryService;
    private BrandService brandService;
    private CartService cartService;

    public MainController(ProductService productService, CategoryService categoryService, BrandService brandService, CartService cartService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.cartService = cartService;
    }

    @RequestMapping("/")
    public String indexPage(Model model) {
        model.addAttribute("activePage", "Main ");
        List<ProductRepr> products = productService.findAll();
        List<CategoryRepr> categories = categoryService.findAll();
        List<BrandRepr> brands = brandService.findAll();
        model.addAttribute("lineItems", cartService.getLineItems());
        model.addAttribute("subTotal", cartService.getSubTotal());
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        model.addAttribute("brands", brands);
        return "index";
    }


}
