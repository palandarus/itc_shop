package ru.geekbrains.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;
import ru.geekbrains.controllers.repr.ProductRepr;
import ru.geekbrains.entities.Picture;
import ru.geekbrains.entities.Product;
import ru.geekbrains.error.NotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface ProductService {

    public Optional<ProductRepr> findById(Long id);

    public void deleteById(Long id);

    public Page<Product> findAll(Specification<Product> spec, int page, int size);




    public void getProductByMaxPrice() ;

    public List<ProductRepr> findAll() ;

    public void remove(Long id);

}
