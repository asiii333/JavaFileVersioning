
package com.parsing.structure;

import com.parsing.parser.JavaParser;

public class MainMethodDeclaration extends MethodDeclaration {

    MainMethodDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        super(ctx.memberDeclaration().methodDeclaration());
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("int main(int argc, char *argv[])");
        b.append(methodBody);
        return b.toString();
    }

}
