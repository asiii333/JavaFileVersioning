package com.parsing.structure;

import com.parsing.parser.JavaParser;
import java.util.LinkedList;
import java.util.List;

public class Block extends SyntaxTreeElement {

    private final JavaParser.BlockContext ctx;
    private final List<BlockStatement> blockStatements = new LinkedList<>();

    public Block(JavaParser.BlockContext ctx) {
        super(ctx);
        this.ctx = ctx;
        for (JavaParser.BlockStatementContext bsCtx : ctx.blockStatement()) {
            blockStatements.add(new BlockStatement(bsCtx));
        }
    }

    public void addBlockStatemnsts(List<BlockStatement> list) {
        blockStatements.addAll(list);
    }

    public List<BlockStatement> getBlockStatements() {
        return blockStatements;
    }
}
