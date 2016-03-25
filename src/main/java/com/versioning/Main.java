package com.versioning;

import com.versioning.xml.FilesContainer;

/**
 * Created by Asia on 2016-03-21.
 */
public class Main {
    public final static String PATH = "C:\\Users\\Asia\\Documents\\JAVA\\10_semestr\\JavaFileVersioning\\src\\test\\testdir";

    public static void main (String[] args){

        XMLgenerator xml = new XMLgenerator(PATH);
        FilesContainer container = xml.generateXMLConfigFile();
        CommentManager manager = new CommentManager();
        manager.addComments(container);
    }
}
