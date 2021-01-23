package com.klasevich.lb.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    private static final Logger logger = LogManager.getLogger();
    private static final String DEFAULT_FILE = "data/truck.txt";

    public List<String> readingFromFile(String fileName) {
        List<String> truckData = new ArrayList<>();
        Path path;
        long size = 0L;

        if (fileName != null) {
            path = Paths.get(fileName);
        } else {
            path = Paths.get(DEFAULT_FILE);
        }
        try {
            size = Files.size(path);
            if (size < 0) {
                logger.error("file is empty. Let's check default file");
                path = Paths.get(DEFAULT_FILE);
            }
        } catch (IOException e) {
            logger.error(e);
        }
        try (Stream<String> stream = Files.lines(path)) {
            truckData = stream.collect(Collectors.toList());
        } catch (IOException e) {
            logger.error(e);
        }
        return truckData;
    }
}
