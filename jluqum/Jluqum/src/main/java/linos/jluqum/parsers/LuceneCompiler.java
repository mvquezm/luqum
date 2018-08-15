package linos.jluqum.parsers;

import linos.jluqum.observerInterface.TreeObserver;
import linos.jluqum.tree.TreeWalk;

import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.runtime.tree.CommonTree;


public class LuceneCompiler extends AbstractCompiler {

    private TokenRewriteStream tokens;
    private LuceneGrammarParser parser;

    private TreeObserver treeObserver;

    public void compile(String expression) {
        try {

            ANTLRStringStream input = new ANTLRStringStream(expression);
            this.tokens = new TokenRewriteStream(new LuceneGrammarLexer(input));
            this.parser = new LuceneGrammarParser(tokens);


        } catch (Exception e) {
            throw new IllegalStateException("Bad Grammar.");
        }

    }

    public CommonTree TreeForContex(String contex) {
        try {
            CommonTree ast = null;
            switch (contex) {
                case "Query":

                    ast = (CommonTree) this.parser.mainQ().getTree();

                    break;
                case "Or":

                    ast = (CommonTree) this.parser.clauseOr().getTree();

                    break;

                case "And":

                    ast = (CommonTree) this.parser.clauseAnd().getTree();

                    break;
                case "Not":

                    ast = (CommonTree) this.parser.clauseNot().getTree();

                    break;
                default:
                    ast = (CommonTree) this.parser.mainQ().getTree();
                    break;

            }

            CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
            nodes.setTokenStream(tokens);

            return ast;

        } catch (RecognitionException e) {
            throw new IllegalStateException("Recognition exception is never thrown, only declared.");
        }

    }

    public TreeObserver getTreeObserver() {
        return treeObserver;
    }

    public void setTreeObserver(TreeObserver treeObserver) {
        this.treeObserver = treeObserver;
    }

    public String TransformQuery(CommonTree tree, TreeObserver treeObserver) {
        this.setTreeObserver(treeObserver);
        TreeWalk walkObservable = new TreeWalk();
        walkObservable.addObserver(getTreeObserver());
        walkObservable.walk(tree);

        return this.tokens.toString();
    }
}
