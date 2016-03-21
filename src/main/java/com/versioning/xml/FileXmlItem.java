package com.versioning.xml;

import java.util.Objects;

public class FileXmlItem {

    private String name;
    private int methods;
    private float version;

    @Override
    public boolean equals(Object object) {
        if (object != null && object instanceof FileXmlItem) {
            return (this.name == null ? ((FileXmlItem) object).name == null : this.name.equals(((FileXmlItem) object).name));
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append("<file>").append(NEW_LINE);
        result.append("<name>").append(name).append("</name>").append(NEW_LINE);
        result.append("<methods>").append(methods).append("</methods>").append(NEW_LINE);
        result.append("<version>").append(version).append("</version>").append(NEW_LINE);
        result.append("</file>");

        return result.toString();
    }
    
    //getters & setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMethods() {
        return methods;
    }

    public void setMethods(int methods) {
        this.methods = methods;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }
}
