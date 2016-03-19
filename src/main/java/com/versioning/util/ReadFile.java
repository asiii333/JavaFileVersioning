package com.versioning.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Asia on 2016-03-19.
 */
public class ReadFile {

    /**
     * read all java file path from argument path
     * @param path
     * @return list of path
     */
    public List<Path> getAllJavaFilePath(String path) throws IOException {
        List<Path> collect = Files.walk(Paths.get(path))
                .filter(path2 -> path2.endsWith(".java"))
                .collect(Collectors.toList());
        return collect;

    }
}
