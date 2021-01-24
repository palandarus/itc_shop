package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.entities.Brand;
import ru.geekbrains.entities.Category;
import ru.geekbrains.entities.Picture;
import ru.geekbrains.entities.Product;
import ru.geekbrains.error.NotFoundException;
import ru.geekbrains.repositories.BrandRepository;
import ru.geekbrains.repositories.CategoryRepository;
import ru.geekbrains.repositories.ProductRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductRepository productRepository;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private BrandService brandService;
    private BrandRepository brandRepository;

    public ProductServiceImpl() {
    }

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, BrandService brandService, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.categoryService=categoryService;
        this.brandService=brandService;
        this.categoryRepository=categoryRepository;
        this.brandRepository=brandRepository;
    }

    public Optional<ProductRepr> findById(Long id) {
        return productRepository.findById(id).map(ProductRepr::new);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }


    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void getProductByMaxPrice() {
        List<Product> productByMaxPrice = productRepository.getProductByMaxPrice();
        System.out.println(productByMaxPrice);
    }

    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }
}



