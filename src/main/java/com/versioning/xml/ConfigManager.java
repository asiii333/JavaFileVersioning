package com.versioning.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class ConfigManager {

    private static final String configFileName = "jfvconfig.xml";
    private String projectPath;
    private FilesContainer files = new FilesContainer();

    public ConfigManager(String projectPath) {
        this.projectPath = projectPath;
    }

    public void saveXml() {
        String xml = getXmlContent();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();

            Document document = builder.parse(new InputSource(new StringReader(xml)));

            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer aTransformer = tranFactory.newTransformer();
            Source src = new DOMSource(document);
            Result dest = new StreamResult(new File(getFullConfigPath()));
            aTransformer.transform(src, dest);
            System.out.println("Xml created successfully");
        } catch (Exception e) {
            System.out.println("Ooops");
            e.printStackTrace();
        }
    }

    public boolean loadFilesFromConfig() throws IOException {
        File f = new File(getFullConfigPath());
        if (!f.exists() ) {
            FileXmlItem item = new FileXmlItem();
            files.addFile(item);
            return false;
        }
        byte[] encoded = Files.readAllBytes(Paths.get(getFullConfigPath()));
        String xmlContent = new String(encoded, Charset.defaultCharset());

        
        files = new FilesContainer();
        
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            ByteArrayInputStream bis = new ByteArrayInputStream(xmlContent.getBytes());
            Document doc = db.parse(bis);
            Node n = doc.getFirstChild();
            NodeList nl = n.getChildNodes();

            for (int i = 0; i < nl.getLength(); i++) {
                Node an = nl.item(i);
                if (an.getNodeType() == Node.ELEMENT_NODE) {
                    Element docElement = (Element)an;
                    
                    FileXmlItem item = new FileXmlItem();
                    item.setName(docElement.getElementsByTagName("name").item(0).getTextContent());
                    item.setPrivatemethods(Integer.parseInt(docElement.getElementsByTagName("privatemethods").item(0).getTextContent()));
                    item.setPublicmethods(Integer.parseInt(docElement.getElementsByTagName("publicmethods").item(0).getTextContent()));
                    item.setVersion(docElement.getElementsByTagName("version").item(0).getTextContent());
                    files.addFile(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    private String getXmlContent() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>").append(NEW_LINE);
        result.append(files.toString());

        return result.toString();
    }

    private String getFullConfigPath() {
        return projectPath + "\\" + configFileName;
    }

    //getters & setters
    public FilesContainer getFiles() {
        return files;
    }

    public void setFiles(FilesContainer files) {
        this.files = files;
    }

}
