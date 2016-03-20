
package com.parsing.output;


import com.parsing.structure.ClassDeclaration;
import com.parsing.structure.MethodDeclaration;

import java.util.Stack;

public class ContextHolder {
    public static final Stack<ClassDeclaration> classDeclarations = new Stack<>();
    public static MethodDeclaration methodDeclaration;
}
