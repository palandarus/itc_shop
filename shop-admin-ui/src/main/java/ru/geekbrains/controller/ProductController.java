package ru.geekbrains.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.geekbrains.controller.repr.BrandRepr;
import ru.geekbrains.controller.repr.CategoryRepr;
import ru.geekbrains.controller.repr.ProductRepr;
import ru.geekbrains.entities.Brand;
import ru.geekbrains.entities.Category;
import ru.geekbrains.entities.Product;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.services.BrandService;
import ru.geekbrains.services.CategoryService;
import ru.geekbrains.services.ProductService;

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
    public String adminProductsPage(Model model) {
        List<ProductRepr> products = productService.findAll();
        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/products/create")
    public String adminProductCreatePage(Model model) {
        List<CategoryRepr> categoryList=categoryService.findAll();
        List<BrandRepr> brandList=brandService.findAll();
        model.addAttribute("product", new ProductRepr());
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        return "product_form";
    }

    @GetMapping("/products/{id}/edit")
    public String adminProductEditPage(@PathVariable Long id, Model model) {

        ProductRepr p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for edit)"));
        List<CategoryRepr> categoryList=categoryService.findAll();
        List<BrandRepr> brandList=brandService.findAll();
        model.addAttribute("product", p);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        return "product_form";
    }

    @PostMapping("/product")
    public String adminUpsertProduct(
            @Valid @ModelAttribute ProductRepr productRepr, RedirectAttributes redirectAttributes
    ) {
       try {
            productService.saveOrUpdate(productRepr);
        } catch (Exception ex) {
            logger.error("Problem with creating product", ex);
            redirectAttributes.addFlashAttribute("error", true);
           if (productRepr.getId() == null) {
               return "redirect:/product/create";
           }
           return "redirect:/product/" + + productRepr.getId()  + "/edit" ;
        }
        return "redirect:/products";
    }


    @DeleteMapping("/products/{id}/remove")
    public String adminDeleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}
