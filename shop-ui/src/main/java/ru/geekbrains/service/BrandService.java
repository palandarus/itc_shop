package ru.geekbrains.service;

import ru.geekbrains.controllers.repr.BrandRepr;
import ru.geekbrains.entities.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {


    public List<BrandRepr> findAll() ;
    public Brand saveOrUpdate(Brand brand);

    public Optional<BrandRepr> findById(Long id);

    public void deleteById(Long id);

}
