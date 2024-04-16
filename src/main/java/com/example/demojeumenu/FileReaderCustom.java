package com.example.demojeumenu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe permettant de lire un fichier
 */
public class FileReaderCustom {
    /**
     * Méthode permettant de lire un fichier
     * @param file Le chemin du fichier
     * @return String
     * @throws IOException Si le fichier n'est pas trouvé
     */
    public static String readFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
