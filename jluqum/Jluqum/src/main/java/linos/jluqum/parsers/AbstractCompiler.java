package linos.jluqum.parsers;

import org.antlr.runtime.tree.CommonTree;


public abstract class AbstractCompiler  {

    public AbstractCompiler() {
        super();
    }

    public abstract void compile(String expression);


    protected void printTree(CommonTree ast) {
        print(ast, 0);
    }

    private void print(CommonTree tree, int level) {

        for (int i = 0; i < level; i++)
            System.out.print("--");

        if (tree==null) {
            System.out.println(" null tree.");
            return ;
        }


        System.out.println(" " + tree.getType() + " " + tree.getText());


        if (tree.getChildren() != null)
            for (Object ie : tree.getChildren()) {
                print((CommonTree) ie, level + 1);
            }
    }
}
