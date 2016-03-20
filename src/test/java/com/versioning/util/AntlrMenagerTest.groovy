package com.versioning.util

import com.versioning.data.FileContent
import com.versioning.data.FileInfo
import spock.lang.Specification

import java.nio.file.Path

/**
 * Created by Asia on 2016-03-20.
 */
class AntlrMenagerTest extends Specification {

    FileManager manageFile = new FileManager();
    List<Path> filesList = manageFile.getAllJavaFilePath("C:\\Users\\Asia\\Documents\\JAVA\\10_semestr\\JavaFileVersioning\\src\\test\\testdir\\recursive1\\recursive2");
    FileContent file = manageFile.getFilesInfo(filesList.get(0));
    AntlrMenager antlrMenager;

    def "GetFileInfo"() {
        given:
        antlrMenager = new AntlrMenager();
        when:
        FileInfo info = antlrMenager.getFileInfo(file);
        then:
        info.path == "C:\\Users\\Asia\\Documents\\JAVA\\10_semestr\\JavaFileVersioning\\src\\test\\testdir\\recursive1\\recursive2\\Example4.java"
        info.privateMethod == 1;
        info.publicMethod == 1;
    }
}
