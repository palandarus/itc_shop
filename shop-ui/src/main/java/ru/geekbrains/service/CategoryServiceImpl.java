package ru.geekbrains.service;

import org.springframework.stereotype.Service;
import ru.geekbrains.controllers.repr.CategoryRepr;
import ru.geekbrains.entities.Category;
import ru.geekbrains.error.NotFoundException;
import ru.geekbrains.repositories.CategoryRepository;
import ru.geekbrains.repositories.ProductRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(
            CategoryRepository categoryRepository
    ) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryRepr> findAll() {
        return categoryRepository.findAll().stream()
                .map(CategoryRepr::new)
                .collect(Collectors.toList());
    }

    public List<Category> findAllCategory() {
        return categoryRepository.findAll();
    }



    public Category saveOrUpdate(CategoryRepr categoryRepr) {
        Category category = (categoryRepr.getId() != null) ? categoryRepository.findById(categoryRepr.getId())
                .orElseThrow(NotFoundException::new) : new Category();
        category.setName(categoryRepr.getName());
        category.setCode(categoryRepr.getCode());
        return categoryRepository.save(category);
    }

    public Optional<CategoryRepr> findById(Long id) {
        return categoryRepository.findById(id).map(CategoryRepr::new);
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

}
