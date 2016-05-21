package com.parsing.structure;


import com.parsing.parser.JavaParser;

import java.util.LinkedList;
import java.util.List;

public class ExpressionList extends SyntaxTreeElement {

    private final JavaParser.ExpressionListContext ctx;
    private final List<Expression> list = new LinkedList<>();

    public ExpressionList(JavaParser.ExpressionListContext ctx) {
        super(ctx);
        this.ctx = ctx;
        for (JavaParser.ExpressionContext expCon : ctx.expression()) {
            list.add(new Expression(expCon));
        }
    }
}
