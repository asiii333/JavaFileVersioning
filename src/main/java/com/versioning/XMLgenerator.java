package com.versioning;

import com.versioning.data.FileContent;
import com.versioning.data.FileInfo;
import com.versioning.data.FileXmlItem;
import com.versioning.util.AntlrMenager;
import com.versioning.util.FileManager;
import com.versioning.xml.ConfigManager;
import com.versioning.xml.FilesContainer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class XMLgenerator {

    private FilesContainer filesContainer = new FilesContainer();
    private FilesContainer generatedFilesContainer = new FilesContainer();
    private AntlrMenager antlrMenager = new AntlrMenager();
    ConfigManager configManager;

    private List<FileInfo> filesList = new ArrayList<>();
    FileManager fileManager = new FileManager();
    List<Path> pathsList = new ArrayList<>();
    private String path;

    public XMLgenerator(String path) {
        configManager = new ConfigManager(path);
        this.path = path;
    }

    public FilesContainer generateXMLConfigFile() throws IOException {
        getAllJavaFile();
        generateFileInfo();
        getXMLConfigFile();
        generateXmlContent();
        saveGeneratedXML();

        return generatedFilesContainer;
    }

    private void generateXmlContent() {

        for (FileInfo file : filesList) {
            FileXmlItem config = filesContainer.getXmlWithTheSameName(file.getPath());
            FileXmlItem newItem = generateNewXmlFile(config, file);
            generatedFilesContainer.addFile(newItem);
        }

    }

    private FileXmlItem generateNewXmlFile(FileXmlItem config, FileInfo file) {
        FileXmlItem newItem = new FileXmlItem();
        String version = getFileVersion(config, file);
        newItem.setVersion(version);
        newItem.setPublicmethods(file.getPublicMethod());
        newItem.setPrivatemethods(file.getPrivateMethod());
        newItem.setName(file.getPath());
        boolean commented = checkIfFileWasCommented(config);
        newItem.setCommented(commented);
        return newItem;
    }

    private boolean checkIfFileWasCommented(FileXmlItem config) {
        boolean commented = false;
        if (config != null) {
            commented = true;
        }
        return commented;
    }

    private String getFileVersion(FileXmlItem config, FileInfo file) {
        String version = "1.0";
        if (config != null) {
            version = config.getVersion();
        } else {
            return version;
        }
        int firstPart = Integer.valueOf(version.split("\\.")[0]);
        int secondPart = Integer.valueOf(version.split("\\.")[1]);
        if (config.getPrivatemethods() != file.getPrivateMethod()) {
            secondPart++;
        }
        if (config.getPublicmethods() != file.getPublicMethod()) {
            firstPart++;
            secondPart = 0;
        }
        version = firstPart + "." + secondPart;
        return version;
    }

    private void saveGeneratedXML() {
        configManager.setFiles(generatedFilesContainer);
        configManager.saveXml();
    }

    private void getXMLConfigFile() throws IOException {
        configManager.loadFilesFromConfig();
        filesContainer = configManager.getFiles();
    }

    private void generateFileInfo() throws IOException {
        for (Path path : pathsList) {
            FileContent content = fileManager.getFilesInfo(path);
            FileInfo info = antlrMenager.getFileInfo(content);
            filesList.add(info);
        }
    }

    private void getAllJavaFile() throws IOException {
        pathsList = fileManager.getAllJavaFilePath(path);
    }
}
