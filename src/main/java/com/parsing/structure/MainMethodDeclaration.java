
package com.parsing.structure;

import com.parsing.parser.JavaParser;

public class MainMethodDeclaration extends MethodDeclaration {
    MainMethodDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        super(ctx.memberDeclaration().methodDeclaration());
    }
}
