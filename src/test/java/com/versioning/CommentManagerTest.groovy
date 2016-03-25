package com.versioning

import com.versioning.xml.FilesContainer
import spock.lang.Specification

/**
 * Created by Asia on 2016-03-22.
 */
class CommentManagerTest extends Specification {
    FilesContainer container;

    def setup(){
        XMLgenerator xml = new XMLgenerator();
        xml.generateXMLConfigFile();
        container = xml.generatedFilesContainer;
    }

    def "AddComments"() {
        given:
        CommentManager manager = new CommentManager();
        when:
        manager.addComments(container);
        then:
        true;
    }
}
