package linos.jluqum.parsers;

import org.antlr.runtime.tree.CommonTree;


public abstract class AbstractCompiler  {

    public AbstractCompiler() {
        super();
    }

    public abstract void compile(String expression);



}
