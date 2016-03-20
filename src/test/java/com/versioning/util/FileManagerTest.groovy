package com.versioning.util

import com.versioning.data.FileContent
import spock.lang.Specification

import java.nio.file.Path

/**
 * Created by Asia on 2016-03-19.
 */
class FileManagerTest extends Specification {

    FileManager manageFile = new FileManager();

    def "GetAllJavaFilePath"() {
        given:
        List<Path> filesList;
        when:
        filesList =  manageFile.getAllJavaFilePath("C:\\Users\\Asia\\Documents\\JAVA\\10_semestr\\JavaFileVersioning\\src\\test\\testdir");
        then:
        filesList.size() == 3;

    }

    def "getFilesInfoTest"(){
        given:
        List<Path> filesList = manageFile.getAllJavaFilePath("C:\\Users\\Asia\\Documents\\JAVA\\10_semestr\\JavaFileVersioning\\src\\test\\testdir\\recursive1\\recursive2");
        when:
        FileContent file = manageFile.getFilesInfo(filesList.get(0));
        then:
        file.sourceCode == "public class Example4{\r\n    private int conts;\r\n    public void method(){}\r\n    private void method2(){}\r\n}"
        file.path == "C:\\Users\\Asia\\Documents\\JAVA\\10_semestr\\JavaFileVersioning\\src\\test\\testdir\\recursive1\\recursive2\\Example4.java"
    }
}
