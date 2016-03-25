package com.versioning.xml;

/**
 *
 * @author mmate
 */
import java.io.File;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.versioning.data.FileXmlItem;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlGenerator {

    private static String projectPath = "C:\\Users\\Asia\\Documents\\JAVA\\10_semestr\\JavaFileVersioning\\src\\test\\testdir";

    private static String xmlTemplate = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<files><file></file></files>";
    private static String configFileName = "jfvconfig.xml";
    private static String fullConfigPath = projectPath + "\\" + configFileName;

    public static void main(String[] args) {
        //getConfigFile();
        ConfigManager configManager = new ConfigManager(projectPath);
        try {
            //getConfigFile();
            configManager.loadFilesFromConfig();
            FilesContainer container = configManager.getFiles();
            //configManager.setFiles(getTestFileContainer());
            //configManager.saveXml();
            int i = 1;
        } catch (Exception ex) {
            Logger.getLogger(XmlGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Zwraca plik konfiguracyjny, tworzy go jeśli nie istnieje
     */
    private static void getConfigFile() {
        File f = new File(fullConfigPath);
        if (f.exists() && !f.isDirectory()) {
            System.out.println("File found");
        } else {
            System.out.println("File not found");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();

                Document document = builder.parse(new InputSource(new StringReader(xmlTemplate)));

                TransformerFactory tranFactory = TransformerFactory.newInstance();
                Transformer aTransformer = tranFactory.newTransformer();
                Source src = new DOMSource(document);
                Result dest = new StreamResult(new File(fullConfigPath));
                aTransformer.transform(src, dest);
                System.out.println("Xml created successfully");
            } catch (Exception e) {
                System.out.println("Ooops");
                e.printStackTrace();
            }
        }
    }

/*    private static FilesContainer getTestFileContainer() {
        FilesContainer container = new FilesContainer();

        for (int i = 0; i < 5; i++) {
            FileXmlItem item = new FileXmlItem();
            item.setName("item2_" + Integer.toString(i));
            //item.setMethods(i*4);
            item.setVersion(new Float(1.5));
            
            container.addFile(item);
        }

        return container;
    }*/
}
