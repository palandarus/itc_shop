package ru.geekbrains.services;

import org.springframework.stereotype.Service;
import ru.geekbrains.controller.repr.BrandRepr;
import ru.geekbrains.entities.Brand;
import ru.geekbrains.entities.Category;
import ru.geekbrains.repositories.BrandRepository;
import ru.geekbrains.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandService {

    private BrandRepository brandRepository;
    private ProductRepository productRepository;

    public BrandService(
            BrandRepository categoryRepository,
            ProductRepository productRepository
    ) {
        this.brandRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<BrandRepr> findAll() {
        return brandRepository.findAll().stream()
                .map(BrandRepr::new).collect(Collectors.toList());
    }




    public Brand saveOrUpdate(Brand brand){
        return  brandRepository.save(brand);
    }

    public Optional<BrandRepr> findById(Long id){
        return brandRepository.findById(id).map(BrandRepr::new);
    }

    public void deleteById(Long id) {
        brandRepository.deleteById(id);
    }

}
