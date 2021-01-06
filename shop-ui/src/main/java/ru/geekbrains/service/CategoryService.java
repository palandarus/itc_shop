package ru.geekbrains.service;

import ru.geekbrains.controllers.repr.CategoryRepr;
import ru.geekbrains.entities.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {


    public List<CategoryRepr> findAll();

    public List<Category> findAllCategory();


    public Category saveOrUpdate(CategoryRepr categoryRepr);

    public Optional<CategoryRepr> findById(Long id);

    public void deleteById(Long id);

}
