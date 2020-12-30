package ru.geekbrains.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entities.Product;
import ru.geekbrains.exceptions.ResourceNotFoundException;
import ru.geekbrains.services.ProductService;
import ru.geekbrains.utils.ProductFilter;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String showAllProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        productService.getProductByMaxPrice();

        return "products";
    }

    @GetMapping("/product/add")
    public String addProduct(
            Model model
    ) {
        model.addAttribute("product", new Product());
        return "product_create_form";
    }

    @PostMapping("/product/add")
    public String addProduct(
            @Valid @ModelAttribute Product product,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "product_create_form";
        }
        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/product/edit/{id}")
    public String showProductEditForm(@PathVariable Long id, Model model) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("product", p);
        return "product";
    }

    @PostMapping("/product/edit")
    public String showEditForm(@ModelAttribute Product product) {
        productService.saveOrUpdate(product);
        return "redirect:/products";
    }

    @PostMapping("/product/delete/{id}")
    public String deleteOneProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }

}
