package ru.geekbrains.repositories;


import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entities.Picture;

import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("SELECT pd.data FROM PictureData pd WHERE pd = (SELECT p.pictureData FROM Picture p WHERE p.id=:id) AND pd.data IS NOT NULL")
    Optional<byte[]> getPictureDataById(long id);

    @Query("SELECT p.contentType FROM Picture p WHERE p.id = :id AND p.contentType IS NOT NULL")
    Optional<String> getPictureContentTypeById(long id);

    @Query("SELECT pd.pictureFilePath FROM PictureData pd WHERE pd = (SELECT p.pictureData FROM Picture p WHERE p.id=:id) AND pd.pictureFilePath IS NOT NULL")
    String getPictureFilePathById(long id);

}
