package com.klasevich.lb.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private static Logger logger = LogManager.getLogger();
    private static final String SEPARATOR = " ";

    public List<String> parseLines(String line) {
        List<String> lines = new ArrayList<>();
        lines = List.of(line.split(SEPARATOR));
        logger.debug(lines);
        return lines;
    }
}
