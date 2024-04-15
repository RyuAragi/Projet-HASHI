package com.example.demojeumenu;

import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;


/**
 * Classe permettant d'extraire un fichier depuis les ressources
 */
public class ResourceExtractor {
    /**
     * Méthode permettant d'extraire un fichier depuis les ressources
     * @param resourcePath Le chemin du fichier
     * @return File
     * @throws IOException Si le fichier n'est pas trouvé
     */
    public static File extract(String resourcePath) throws IOException {
        InputStream resourceStream = SoundUtils.class.getResourceAsStream(resourcePath);
        if (resourceStream == null) {
            throw new IllegalArgumentException("Resource not found: " + resourcePath);
        }

        Path tempFile = Files.createTempFile(null, null);
        Files.copy(resourceStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        return tempFile.toFile();
    }
}