package com.parsing.structure;

import com.parsing.output.Output;
import com.parsing.parser.JavaParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.LinkedList;
import java.util.List;

public class SwitchLabel extends SyntaxTreeElement {

    private final JavaParser.SwitchLabelContext ctx;
    private final List tokensList = new LinkedList();

    public SwitchLabel(JavaParser.SwitchLabelContext ctx) {
        super(ctx);
        this.ctx = ctx;
        for (ParseTree pt : ctx.children) {
            if (pt instanceof TerminalNode) {
                tokensList.add(pt.getText() + " ");
            } else {
                tokensList.add(pt.getText());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object token : tokensList) {
            if (token.toString().equals("case ") || token.toString().equals("default ")) {
                sb.append(Output.indent(0));
            }
            sb.append(token);
        }
        return sb.toString();
    }
}
