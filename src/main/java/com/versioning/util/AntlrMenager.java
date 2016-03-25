package com.versioning.util;

import com.parsing.util.TranslationUnit;
import com.versioning.data.FileContent;
import com.versioning.data.FileInfo;
import com.parsing.parser.JavaLexer;
import com.parsing.parser.JavaParser;
import com.parsing.structure.ClassBodyDeclaration;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;

import java.util.List;

/**
 * Created by Asia on 2016-03-20.
 */
public class AntlrMenager {
    /**
     * get info about file - numers of methodd etc..
     * @param fileContent
     * @return
     */
    public FileInfo getFileInfo(FileContent fileContent){
        FileInfo fileInfo = new FileInfo();
        try {
            fulfilFileInfo(fileContent,fileInfo);
        } catch (RecognitionException ex) {
            System.out.println("Syntax error: " + ex);
        }

        return fileInfo;
    }

    private void fulfilFileInfo(FileContent fileContent, FileInfo fileInfo ){
        TranslationUnit translationUnit = getTranslationUnit(fileContent);
        int privateMethods = countMethod( translationUnit.classDeclarations.get(0).privateDeclarations);
        int publicMethods = countMethod( translationUnit.classDeclarations.get(0).publicDeclarations);
        fileInfo.setPath(fileContent.path);
        fileInfo.setPublicMethod(publicMethods);
        fileInfo.setPrivateMethod(privateMethods);
    }
    private TranslationUnit getTranslationUnit(FileContent fileContent){
        ANTLRInputStream stream = new ANTLRInputStream(fileContent.sourceCode);
        JavaLexer lexer = new JavaLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        JavaParser.CompilationUnitContext tree = parser.compilationUnit();
        return new TranslationUnit(tree);

    }
    private int countMethod(List<ClassBodyDeclaration> declarations){
        int methodCount = 0;
        for(ClassBodyDeclaration bodyDeclaration: declarations){
            if(bodyDeclaration.methodDecl != null){
                methodCount++;
            }
        }

        return methodCount;
    }
}
