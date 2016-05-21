package com.parsing.structure;

import com.parsing.parser.JavaParser;
import java.util.HashSet;
import java.util.Set;

public class MethodDeclaration extends SyntaxTreeElement {

    protected MethodBody methodBody;
    protected final JavaParser.MethodDeclarationContext ctx;
    private final Set<String> localVariableNames = new HashSet<>();

    public MethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    public void initMethodBody() {
        if (ctx.methodBody() != null) {
            methodBody = new MethodBody(ctx.methodBody());
        }
    }

    String getIdentifier() {
        return ctx.Identifier().getText();
    }
}
