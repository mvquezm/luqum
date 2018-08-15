package linos.jluqum.tree;

import org.antlr.runtime.Token;
import org.antlr.runtime.tree.CommonTree;

import java.util.Observable;


public class TreeWalk extends Observable {

    private Token node;

    void walkAndTransform(CommonTree node) {
        switch (node.getText()) {
            case "QNORMAL":

                setToken(((CommonTree) node.getChild(0)).getToken());

                break;
            case "QPHRASE":

                setToken(((CommonTree) node.getChild(0)).getToken());

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

        if (tree.getChildren() != null) {
            for (Object ie : tree.getChildren()) {
                walk((CommonTree) ie);
            }
        }
    }
}
