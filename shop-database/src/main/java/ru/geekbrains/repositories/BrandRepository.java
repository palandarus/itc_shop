package ru.geekbrains.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entities.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
