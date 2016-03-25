package com.versioning.data;

/**
 * Created by Asia on 2016-03-19.
 */
public class FileInfo {
    private String path;
    private int publicMethod;
    private int privateMethod;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPublicMethod() {
        return publicMethod;
    }

    public void setPublicMethod(int publicMethod) {
        this.publicMethod = publicMethod;
    }

    public int getPrivateMethod() {
        return privateMethod;
    }

    public void setPrivateMethod(int privateMethod) {
        this.privateMethod = privateMethod;
    }
}
