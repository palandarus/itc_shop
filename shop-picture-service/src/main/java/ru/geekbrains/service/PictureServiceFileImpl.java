package ru.geekbrains.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.entities.Picture;
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
public class PictureServiceFileImpl implements PictureService {
    private static final Logger logger = LoggerFactory.getLogger(PictureService.class);
    private static final String DEFAULT_IMAGE_FILE_PATH = ".\\shop-database\\src\\main\\resources\\images\\product\\";

    private PictureRepository repository;

    @Autowired
    public PictureServiceFileImpl(PictureRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<String> getPictureContentTypeById(long id) {
        return repository.findById(id)
                .map(Picture::getContentType);
    }

    @Override
    public Optional<byte[]> getPictureDataById(long id) throws IOException {

        return Optional.of(Files.readAllBytes(Path.of(repository.findById(id).map(pic -> pic.getPictureData().getPictureFilePath()).orElse(""))));
    }

    @Override
    public PictureData createPictureData(byte[] picture) throws IOException {
        String fileName = UUID.randomUUID().toString().replace("-", "");
        boolean flag = true;
        while (flag) {
            flag = false;
            try {
                List<Path> paths = Files.list(Paths.get(DEFAULT_IMAGE_FILE_PATH)).collect(Collectors.toList());
                for (Path path : paths) {
                    if (path.getFileName().toString().equals(fileName)) {
                        fileName = UUID.randomUUID().toString().replace("-", "");
                        flag = true;
                    }
                }
            }catch (IOException e){
                logger.error("Folder is empty "+DEFAULT_IMAGE_FILE_PATH);
                logger.error(e.getMessage());
            }
            finally {
                flag=false;
            }
        }
        String fullFileName = DEFAULT_IMAGE_FILE_PATH + fileName;
        Files.createFile(Paths.get(fullFileName));
        Files.write(Paths.get(fullFileName), picture);
        return new PictureData(fullFileName);
    }
}
