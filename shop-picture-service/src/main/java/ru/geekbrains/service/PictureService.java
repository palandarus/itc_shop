package ru.geekbrains.service;


import org.springframework.stereotype.Service;
import ru.geekbrains.entities.PictureData;

import java.io.IOException;
import java.util.Optional;

public interface PictureService {

    // TODO перенести сюда функционал получения списка картинок

    // TODO перенести сюда функционал для удаления картинок
    void deleteById(Long id) throws IOException;


    Optional<String> getPictureContentTypeById(long id);

    Optional<byte[]> getPictureDataById(long id) throws IOException;

    PictureData createPictureData(byte[] picture) throws IOException;


}
