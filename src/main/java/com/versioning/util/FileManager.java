package com.versioning.util;

import com.versioning.data.FileContent;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Asia on 2016-03-19.
 */
public class FileManager {

    /**
     * read all java file path from argument path
     * @param path
     * @return list of path
     */
    public List<Path> getAllJavaFilePath(String path) throws IOException {
        List<Path> collect = Files.walk(Paths.get(path))
                .filter(path2 -> String.valueOf(path2).endsWith(".java"))
                .collect(Collectors.toList());
        return collect;

    }

    /**
     * Get info about file - absolute path + content
     * @param path
     * @return
     * @throws IOException
     */
    public FileContent getFilesInfo(Path path) throws IOException {
        FileContent fileContent = new FileContent();
        fileContent.sourceCode = FileUtils.readFileToString(path.toFile());
        fileContent.path = path.toAbsolutePath().toString();
        return fileContent;
    }


}
