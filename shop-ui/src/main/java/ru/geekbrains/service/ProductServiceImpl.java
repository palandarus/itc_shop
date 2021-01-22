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
    private PictureService pictureService;
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
    private BrandService brandService;
    private BrandRepository brandRepository;


    public ProductServiceImpl(ProductRepository productRepository, PictureService pictureService, CategoryService categoryService, BrandService brandService, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.pictureService = pictureService;
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


    public Product saveOrUpdate(ProductRepr productRepr) throws IOException {
        Product product = (productRepr.getId() != null) ? productRepository.findById(productRepr.getId())
                .orElseThrow(NotFoundException::new) : new Product();
        Category category=(productRepr.getCategory().getId() != null) ? categoryRepository.findById(productRepr.getCategory().getId())
                .orElseThrow(NotFoundException::new) : new Category();
        Brand brand=(productRepr.getBrand().getId() != null) ? brandRepository.findById(productRepr.getBrand().getId())
                .orElseThrow(NotFoundException::new) : new Brand();
        product.setTitle(productRepr.getName());
        product.setCategory(category);
        product.setBrand(brand);
        product.setPrice(productRepr.getPrice());

        if (productRepr.getNewPictures() != null) {
            for (MultipartFile newPicture : productRepr.getNewPictures()) {
                logger.info("Product {} file {} size {} contentType {}", productRepr.getId(),
                        newPicture.getOriginalFilename(), newPicture.getSize(), newPicture.getContentType());

                if (product.getPictures() == null) {
                    product.setPictures(new ArrayList<>());
                }

                product.getPictures().add(new Picture(
                        newPicture.getOriginalFilename(),
                        newPicture.getContentType(),
                        pictureService.createPictureData(newPicture.getBytes()),
                        product
                ));
            }
        }

        return productRepository.save(product);
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



