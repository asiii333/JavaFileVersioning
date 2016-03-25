/**
 * created by JavaFileVersioning
 * version 1.2
 */
package com.versioning;

import com.versioning.data.FileXmlItem;
import com.versioning.xml.FilesContainer;

import java.io.*;

/**
 * Created by Asia on 2016-03-22.
 */
public class CommentManager {
    private FilesContainer generatedFilesContainer;

    public void addComments(FilesContainer generatedFilesContainer){
        this.generatedFilesContainer = generatedFilesContainer;
        for(FileXmlItem item : generatedFilesContainer.getFiles()){
            String comment = getComment(item);
            try {
                addCommentToFile(comment, item);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void addCommentToFile(String comment,FileXmlItem item) throws IOException {
        if(item.isCommented()){
            replaceComment(comment, item);
        }else{
            addNewComment(comment, item);
        }

    }
    private void addNewComment(String comment,FileXmlItem item) throws IOException {
        File mFile = new File(item.getName());
        FileInputStream fis = new FileInputStream(mFile);
        InputStreamReader inputStreamReader = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(inputStreamReader);
        String result = "";
        String line = "";
        while ((line = br.readLine()) != null){
            result = result + line + "\n";
        }

        result = comment + result;

        mFile.delete();
        FileOutputStream fos = new FileOutputStream(mFile);
        fos.write(result.getBytes());
        fos.flush();
    }
    private void replaceComment(String comment,FileXmlItem item) throws IOException {
        File mFile = new File(item.getName());
        RandomAccessFile f = new RandomAccessFile(mFile, "rw");
        f.seek(0); // to the beginning
        f.write(comment.getBytes());
        f.close();
    }
    private String getComment(FileXmlItem item){
        String prefix = "/**\n" +
                " * created by JavaFileVersioning\n" +
                " * version ";
        String version = item.getVersion();
        String suffix = "\n **/ \n\n";
        return prefix + version + suffix;
    }
}
