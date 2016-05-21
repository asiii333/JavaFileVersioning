package com.parsing.structure;

import com.parsing.parser.JavaParser;

public class BlockStatement extends SyntaxTreeElement {

    private final JavaParser.BlockStatementContext ctx;
    private Statement statement;

    public BlockStatement(JavaParser.BlockStatementContext ctx) {
        super(ctx);
        this.ctx = ctx;
        JavaParser.LocalVariableDeclarationStatementContext varDeclSt = ctx.localVariableDeclarationStatement();

        JavaParser.StatementContext stCtx = ctx.statement();
        if (stCtx != null) {
            statement = new Statement(stCtx);
        }
    }
}
