package com.versioning.xml;

import java.util.ArrayList;

public class FilesContainer {

    private ArrayList<FileXmlItem> files;

    public FilesContainer() {
        files = new ArrayList<>();
    }

    /*
    * Dodaje inforacje o pliku do listy, jeśli istnieje inny o takiej samej nazwie to podmienia go
     */
    public void addFile(FileXmlItem file) {
        if (!files.contains(file)) {
            files.add(file);
        } else {
            files.remove(file);
            files.add(file);
        }
    }

    public void removeFile(FileXmlItem file) {
        files.remove(file);
    }

    public void clearFileList() {
        files.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append("<files>").append(NEW_LINE);
        for (FileXmlItem file : files) {
            result.append(file.toString()).append(NEW_LINE);
        }
        result.append("</files>");

        return result.toString();
    }

    //getters & setters
    public ArrayList<FileXmlItem> getFiles() {
        return files;
    }

    public void setFiles(ArrayList<FileXmlItem> files) {
        this.files = files;
    }
}
