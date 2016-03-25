package com.versioning

import com.versioning.data.FileInfo
import com.versioning.data.FileXmlItem
import com.versioning.xml.XmlGenerator
import spock.lang.Specification

/**
 * Created by Asia on 2016-03-22.
 */
class XMLgeneratorTest extends Specification {

    XMLgenerator xml = new XMLgenerator();

    def "GenerateXMLConfigFile"() {
        when:
        xml.generateXMLConfigFile();
        then:
        true
    }

    def "getFileVersionTest - new class"(){
        given:
        FileInfo info = new FileInfo();
        when:
        def version =  xml.getFileVersion(null, info);
        then:
        version == "1.0"
    }

    def "getFileVersionTest - change number of private method"(){
        given:
        FileInfo info = new FileInfo();
        info.privateMethod = 5;
        FileXmlItem config = new FileXmlItem();
        config.version = "2.5";
        config.privatemethods = 6;
        when:
        def version =  xml.getFileVersion(config, info);
        then:
        version == "2.6"
    }

    def "getFileVersionTest - change number of public method"(){
        given:
        FileInfo info = new FileInfo();
        info.privateMethod = 5;
        info.publicMethod = 5;
        FileXmlItem config = new FileXmlItem();
        config.version = "2.5";
        config.privatemethods = 6;
        config.publicmethods = 6;
        when:
        def version =  xml.getFileVersion(config, info);
        then:
        version == "3.0"
    }

}
