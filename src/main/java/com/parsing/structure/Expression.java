package com.parsing.structure;

import com.parsing.output.ContextHolder;
import com.parsing.parser.JavaParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import java.util.LinkedList;
import java.util.List;

public class Expression extends SyntaxTreeElement {

    private final JavaParser.ExpressionContext ctx;
    private final List<Expression> expressions = new LinkedList<>();
    private final ClassDeclaration parentClass;
    private final MethodDeclaration parentMethod;
    private boolean isParExpression = false;
    private boolean instanceOf = false;

    public Expression(JavaParser.ExpressionContext ctx) {
        super(ctx);
        this.ctx = ctx;
        parentClass = ContextHolder.classDeclarations.peek();
        parentMethod = ContextHolder.methodDeclaration;
        List<JavaParser.ExpressionContext> ctxList = ctx.expression();
        for (JavaParser.ExpressionContext exCtx : ctx.expression()) {
            expressions.add(new Expression(exCtx));
        }
        for (ParseTree pt : ctx.children) {
            if (pt instanceof TerminalNode) {
                if (pt.getText().contains("instanceof")) {
                    instanceOf = true;
                    break;
                }
            }
        }
    }

    public Expression(JavaParser.ParExpressionContext ctx) {
        this(ctx.expression());
        isParExpression = true;
    }

    public void setParExpression(boolean isParExpression) {
        this.isParExpression = isParExpression;
    }
}
