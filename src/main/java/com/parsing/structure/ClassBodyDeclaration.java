package com.parsing.structure;

import com.parsing.output.ContextHolder;
import com.parsing.parser.JavaParser;
import java.util.ArrayList;
import java.util.List;

public class ClassBodyDeclaration extends SyntaxTreeElement {

    JavaParser.ClassBodyDeclarationContext ctx;
    public MethodDeclaration methodDecl;

    public ClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        super(ctx);
        this.ctx = ctx;
        if (ctx.memberDeclaration() != null) {
            if (ctx.memberDeclaration().methodDeclaration() != null) {
                methodDecl = new MethodDeclaration(ctx.memberDeclaration().methodDeclaration());
                ContextHolder.methodDeclaration = methodDecl;
                methodDecl.initMethodBody();
            }
        }
    }

    List<String> getIdentifiers() {
        if (methodDecl != null) {
            List<String> idList = new ArrayList<>();
            idList.add(methodDecl.getIdentifier());
            return idList;
        }
        return new ArrayList<>();
    }
}
