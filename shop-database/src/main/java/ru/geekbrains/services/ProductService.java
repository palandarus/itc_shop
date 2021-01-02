package ru.geekbrains.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Product;
import ru.geekbrains.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Specification<Product> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product saveOrUpdate(Product product) {
        return productRepository.save(product);
    }

    public Product update(Product product) {
        productRepository.save(product);
        return productRepository.getOne(product.getId());
    }

    public void getProductByMaxPrice() {
        List<Product> productByMaxPrice = productRepository.getProductByMaxPrice();
        System.out.println(productByMaxPrice);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public void remove(Long id) {
        productRepository.deleteById(id);
    }
}

