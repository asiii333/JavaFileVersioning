package com.parsing.structure;

import com.parsing.util.TranslationUnit;
import com.parsing.output.ContextHolder;
import com.parsing.util.Utils;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static com.parsing.parser.JavaParser.*;

public final class ClassDeclaration extends SyntaxTreeElement {

    private final TranslationUnit parent;
    private final ClassDeclarationContext ctx;
    private String name;
    public final List<ClassBodyDeclaration> publicDeclarations = new LinkedList<>();
    public final List<ClassBodyDeclaration> privateDeclarations = new LinkedList<>();
    private final List<ClassBodyDeclaration> protectedDeclarations = new LinkedList<>();
    private final Map<String, ClassBodyDeclaration> allDeclarations = new HashMap<>();
    private final List<String> extendImplementList = new LinkedList<>();
    private boolean isAbstract = false;

    public ClassDeclaration(ClassDeclarationContext ctx, TranslationUnit parent) {
        super(ctx);
        this.ctx = ctx;
        this.parent = parent;
    }

    public void initClassContent() {
        name = ctx.Identifier().getText();
        if (ctx.type() != null) {
            extendImplementList.add(ctx.type().getText());
        }
        if (ctx.typeList() != null) {
            for (TypeContext typeCon : ctx.typeList().type()) {
                extendImplementList.add(typeCon.getText());
            }
        }
        if (ctx.parent != null && ctx.parent instanceof TypeDeclarationContext) { // czemu tak?
            if (((TypeDeclarationContext) ctx.parent).classOrInterfaceModifier().toString().contains("abstract")) {
                isAbstract = true;
            }
        }
        for (ClassBodyDeclarationContext cbdCtx : ctx.classBody().classBodyDeclaration()) {
            addDeclaration(cbdCtx);
        }
    }

    public void addDeclaration(ClassBodyDeclarationContext ctx) {
        Utils.checkForExceptions(ctx);

        if (isMainMethod(ctx)) {
            MainMethodDeclaration mainMethDecl = new MainMethodDeclaration(ctx);
            parent.setMainMethod(mainMethDecl);
            ContextHolder.methodDeclaration = mainMethDecl;
            mainMethDecl.initMethodBody();
            return;
        }

        ClassBodyDeclaration classBodyDecl = new ClassBodyDeclaration(ctx);
        if (ctx.modifier().isEmpty()) {
            privateDeclarations.add(classBodyDecl);
            saveAllDeclarations(classBodyDecl);
            return;
        }
        ModifierContext modCtx = ctx.modifier().get(0);

        String modifier = modCtx.classOrInterfaceModifier().getText();

        switch (modifier) {
            case "public":
                ctx.children.remove(modCtx);
                publicDeclarations.add(classBodyDecl);
                break;
            case "protected":
                ctx.children.remove(modCtx);
                protectedDeclarations.add(classBodyDecl);
                break;
            case "private":
                ctx.children.remove(modCtx);
                privateDeclarations.add(classBodyDecl);
                break;
            default:
                privateDeclarations.add(classBodyDecl);
        }

        saveAllDeclarations(classBodyDecl);
    }

    private void saveAllDeclarations(ClassBodyDeclaration classBodyDecl) {
        for (String id : classBodyDecl.getIdentifiers()) {
            allDeclarations.put(id, classBodyDecl);
        }
    }

    private boolean isMainMethod(ClassBodyDeclarationContext ctx) {
        if (ctx.modifier().size() < 2) {
            return false;
        }
        if (!ctx.modifier().get(0).getText().equals("public")
                || !ctx.modifier().get(1).getText().equals("static")) {
            return false;
        }
        MethodDeclarationContext methCtx = ctx.memberDeclaration().methodDeclaration();
        if (methCtx == null || !methCtx.Identifier().getText().equals("main")) {
            return false;
        }
        ParseTree childCtx = methCtx.getChild(0);
        return childCtx.getText().equals("void");
    }

    public ClassBodyDeclaration getDeclaration(String name) {
        return allDeclarations.get(name);
    }
}
