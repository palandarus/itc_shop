package ru.geekbrains.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.geekbrains.controllers.repr.BrandRepr;
import ru.geekbrains.controllers.repr.CategoryRepr;
import ru.geekbrains.entities.Brand;
import ru.geekbrains.entities.Category;
import ru.geekbrains.repositories.BrandRepository;
import ru.geekbrains.repositories.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void init() {
        categoryRepository = mock(CategoryRepository.class);
        CategoryServiceImpl impl = new CategoryServiceImpl(categoryRepository);
        categoryService = impl;
    }

    @Test
    public void testFindById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");

        when(categoryRepository.findById(eq(1L)))
                .thenReturn(Optional.of(expectedCategory));

        Optional<CategoryRepr> opt = categoryService.findById(1L);

        assertTrue(opt.isPresent());
        assertEquals(expectedCategory.getId(), opt.get().getId());
        assertEquals(expectedCategory.getName(), opt.get().getName());
    }

    @Test
    public void testDeleteById() {
        Category expectedCategory = new Category();
        expectedCategory.setId(1L);
        expectedCategory.setName("Category name");
        categoryService.deleteById(1L);

        Optional<CategoryRepr> opt = categoryService.findById(1L);

        assertTrue(opt.isEmpty());
    }

    @Test
    public void testFindAll(){
        List<Category> categoryList=new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            categoryList.add(new Category());
            categoryList.get(i).setId(1L*i);
            categoryList.get(i).setName("Brand name "+i);
        }
        when(categoryRepository.findAll())
                .thenReturn(categoryList);
        List<CategoryRepr> categoryRepr= categoryService.findAll();
        for (int i = 0; i < categoryRepr.size(); i++) {
            assertEquals(categoryList.get(i).getId(), categoryRepr.get(i).getId());
            assertEquals(categoryList.get(i).getName(), categoryRepr.get(i).getName());
        }
    }

}
