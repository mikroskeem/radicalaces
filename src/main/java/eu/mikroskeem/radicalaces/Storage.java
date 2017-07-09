package eu.mikroskeem.radicalaces;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Storage {
    private final Path path;
    private final Properties storage;

    public Storage(String filename) throws IOException {
        path = Paths.get(System.getProperty("user.home"), filename);
        if(Files.notExists(path)) {
            Files.createFile(path);
        }
        storage = new Properties();
        try(InputStream is = Files.newInputStream(path)) {
            storage.load(is);
        }
    }

    public void save() throws IOException {
        try(OutputStream os = Files.newOutputStream(path)) {
            storage.store(os, "Radical Aces Classic\nDO NOT EDIT BY HAND!");
        }
    }

    public String get(String key) {
        return storage.getProperty(key);
    }

    public String get(String key, String def) {
        return storage.getProperty(key, def);
    }

    public void put(String key, String value) {
        storage.setProperty(key, value);
    }
}
