package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.PictureData;
import ru.geekbrains.repositories.PictureRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
//@ConditionalOnProperty(value = "${picture.storage.type}", havingValue = "file")   // TODO не видит сервис Parameter 1 of constructor in ru.geekbrains.services.ProductService required a bean of type 'ru.geekbrains.service.PictureService' that could not be found.
public class PictureServiceFileImpl implements PictureService {
    private static final Logger logger = LoggerFactory.getLogger(PictureService.class);
//    private static final String DEFAULT_IMAGE_FILE_PATH = ".\\shop-database\\src\\main\\resources\\images\\product\\";

    @Value("${picture.storage.path}")
    private String storagePath;

    private PictureRepository repository;

    @Autowired
    public PictureServiceFileImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return repository.getPictureContentTypeById(id);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) throws IOException {
        return Optional.of(
                Files.readAllBytes(
                        Path.of(repository.getPictureFilePathById(id))));
    }

    @Override
    public PictureData createPictureData(byte[] picture) throws IOException {
        String fileName = UUID.randomUUID().toString().replace("-", "");
        boolean flag = true;
        while (flag) {
            flag = false;
            try {
                List<Path> paths = Files.list(Paths.get(storagePath)).collect(Collectors.toList());
                for (Path path : paths) {
                    if (path.getFileName().toString().equals(fileName)) {
                        fileName = UUID.randomUUID().toString().replace("-", "");
                        flag = true;
                    }
                }
            } catch (IOException e) {
                logger.error("Folder is empty " + storagePath);
                logger.error(e.getMessage());
            } finally {
                flag = false;
            }
        }
        String fullFileName = storagePath + fileName;
        Files.createFile(Paths.get(fullFileName));
        Files.write(Paths.get(fullFileName), picture);
        return new PictureData(fullFileName);
    }

    @Override
    public void deleteById(Long id) throws RuntimeException {
        String pictureFilePath = repository.getPictureFilePathById(id);
        try {
            Files.delete(Paths.get(pictureFilePath));
            repository.deleteById(id);
        } catch (IOException ex) {
            logger.error("FileNotFound by path - " + pictureFilePath);
            throw new RuntimeException("Cant remove file with path - " + pictureFilePath);
        }

    }

}
