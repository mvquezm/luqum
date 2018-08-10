package linos.jluqum.tree;

import org.antlr.runtime.tree.CommonTree;

import java.util.HashMap;

/**
 * Created by jorge.hernandez on 10/08/2018.
 */
public class TreeWalk {

    HashMap<String, String> transform;

    public TreeWalk(HashMap<String, String> transform) {
        this.transform = transform;
    }

    void WalkAndTransform(CommonTree node) {
        switch (node.getType()) {
            case 22:
                if (transform.containsKey(node.getText())) {
                    node.getToken().setText(transform.get(node.getText()));
                }
                break;
        }
    }

   public void Walk(CommonTree tree) {
        if (tree == null) {
            return;
        }
        WalkAndTransform(tree);

        if (tree.getChildren() != null)
            for (Object ie : tree.getChildren()) {
                Walk((CommonTree) ie);
            }
    }

}
