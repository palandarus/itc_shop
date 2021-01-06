package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Picture;
import ru.geekbrains.entities.PictureData;
import ru.geekbrains.repositories.PictureRepository;

import java.util.Optional;

//@Service
//@ConditionalOnProperty(value = "${picture.storage.type}", havingValue = "database")
public class PictureServiceBlobImpl implements PictureService {

    private final PictureRepository repository;

    @Autowired
    public PictureServiceBlobImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return repository.getPictureContentTypeById(id);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) {

        return repository.getPictureDataById(id);
    }

    @Override
    public PictureData createPictureData(byte[] picture) {
        return new PictureData(picture);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
