package ru.geekbrains.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Category;
import ru.geekbrains.entities.Product;
import ru.geekbrains.repositories.CategoryRepository;
import ru.geekbrains.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;
    private ProductRepository productRepository;

    public CategoryService(
            CategoryRepository categoryRepository,
            ProductRepository productRepository
    ) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<Category> getCategoryByCode(String code) {
        return categoryRepository.findCategoriesByCode(code);
    }

    public Page<Product> getProductsByCategory(String code, Pageable pageable) {
        return productRepository.findProductByCategoryCode(code, pageable);
    }

    public Category saveOrUpdate(Category category){
        return  categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id){
        return categoryRepository.findById(id);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}