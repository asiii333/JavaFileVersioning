package com.versioning.data;

import java.util.Objects;

public class FileXmlItem {

    private String name = "";
    private int privatemethods;
    private int publicmethods;
    private String version = "";
    private boolean commented = false;

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
        result.append("<privatemethods>").append(privatemethods).append("</privatemethods>").append(NEW_LINE);
        result.append("<publicmethods>").append(publicmethods).append("</publicmethods>").append(NEW_LINE);
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getPublicmethods() {
        return publicmethods;
    }

    public void setPublicmethods(int publicmethods) {
        this.publicmethods = publicmethods;
    }

    public int getPrivatemethods() {
        return privatemethods;
    }

    public void setPrivatemethods(int privatemethods) {
        this.privatemethods = privatemethods;
    }

    public boolean isCommented() {
        return commented;
    }

    public void setCommented(boolean commented) {
        this.commented = commented;
    }
}
