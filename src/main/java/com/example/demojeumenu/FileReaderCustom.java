package com.example.demojeumenu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReaderCustom {
    public static String readFileAsString(String file) throws IOException {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
