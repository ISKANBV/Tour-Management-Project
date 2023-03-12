package az.adnsu.tourmanagementproject.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class ImageUploader {

    private static final String IMAGE_DIR = "/var/www/html/images/";

    public String uploadImage(MultipartFile file) throws IOException {

        // Generate a unique filename to avoid conflicts
        String filename = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Save the file to the image directory
        File imageFile = new File(IMAGE_DIR + filename);
        file.transferTo(imageFile);

        String path = IMAGE_DIR + filename;
        // Return the filename to be stored in the database
        return path;
    }
}
