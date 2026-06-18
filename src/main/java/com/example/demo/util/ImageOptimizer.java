package com.example.demo.util;

import com.example.demo.exceptions.PhotoProcessingException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

public class ImageOptimizer {

    public static final Map<String, String> PHOTO_FORMAT = Map.of(MediaType.IMAGE_JPEG_VALUE, "jpg");

    public static byte[] compress(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream()) {
            BufferedImage image = ImageIO.read(inputStream);
            if (image == null) {
                throw new PhotoProcessingException("Failed to read image");
            }

            String contentType = Objects.requireNonNull(file.getContentType());
            String format = PHOTO_FORMAT.get(contentType);
            if (format == null) {
                throw new PhotoProcessingException("Unsupported image type: " + contentType);
            }
            return compressPhoto(image, format);
        }
    }

    private static byte[] compressPhoto(BufferedImage image, String format) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Thumbnails.of(image)
                .crop(Positions.CENTER)
                .size(100, 100)
                .outputFormat(format)
                .toOutputStream(outputStream);
        return outputStream.toByteArray();
    }
}
