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

    FilenameFilter javaFilter =  new FilenameFilter() {
        public boolean accept(File dir, String name) {
            return name.endsWith(".java");
        }
    };

    /**
     * read all java file from argument path
     * @param path
     * @return mapa key:file path  value:file content
     */
    public List<Path> getAllJavaFilePath(String path) throws IOException {
        List<Path> collect = Files.walk(Paths.get(path))
                .filter(path2 -> path2.endsWith(".java"))
                .collect(Collectors.toList());
        return collect;

    }

    private File[] getAllFile(String path) {
        File folder = new File("/path/to/files");
        File[] listOfFiles = folder.listFiles(javaFilter);
        return listOfFiles;
    }

    private  Map<String, String> readFile(File[] fileList){
        Map<String, String> javaFile = new HashMap<>(fileList.length);

        for (int i = 0; i < fileList.length; i++) {
            File file = fileList[i];
            try {
                String path = file.getAbsolutePath();
                String content = FileUtils.readFileToString(file);
                javaFile.put(path,content);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return javaFile;
    }
}
