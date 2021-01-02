package ru.geekbrains.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entities.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
