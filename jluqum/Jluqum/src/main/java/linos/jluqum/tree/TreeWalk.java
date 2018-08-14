package linos.jluqum.tree;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import java.util.HashMap;
import java.util.Observable;

/**
 * Created by jorge.hernandez on 10/08/2018.
 */
public class TreeWalk extends Observable {

    private Token node;

    void walkAndTransform(CommonTree node) {

        switch (node.getType()) {
            case 22:

                if (node.getParent().getType() == 12) {

                    setToken( node.getToken());
                }
                break;
            case 34:
                if (node.getParent().getType() == 13) {

                    setToken(node.getToken());
                }
                break;
        }
    }


    public void setToken(Token node) {
        this.node = node;
        setChanged();
        notifyObservers(node);
    }


    public void walk(CommonTree tree) {
        if (tree == null) {
            return;
        }
        walkAndTransform(tree);

        if (tree.getChildren() != null)
            for (Object ie : tree.getChildren()) {
                walk((CommonTree) ie);
            }
    }

}
