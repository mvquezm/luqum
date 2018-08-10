package linos.jluqum.parsers;

import linos.jluqum.tree.TreeWalk;
import org.antlr.runtime.*;
import org.antlr.runtime.tree.*;
import org.antlr.runtime.tree.CommonTree;

import java.util.HashMap;

/**
 * Created by jorge.hernandez on 09/08/2018.
 */
public class LuceneCompiler extends AbstractCompiler {

    private TokenRewriteStream tokens;
    private LuceneGrammarParser parser;

    public void compile(String expression) {
        try {
            //lexer splits input into tokens
            ANTLRStringStream input = new ANTLRStringStream(expression);
            //Token Rewrite for edit
            this.tokens = new TokenRewriteStream(new LuceneGrammarLexer(input));

            //parser generates abstract syntax tree
            this.parser = new LuceneGrammarParser(tokens);


        } catch (Exception e) {
            throw new IllegalStateException("Bad Grammar.");
        }

    }

    public CommonTree TreeForContex(char contex) {
        try {
            CommonTree ast = null;
            switch (contex) {
                case 'Q':

                    ast = (CommonTree) this.parser.mainQ().getTree();

                    break;
                case 'O':

                    ast = (CommonTree) this.parser.clauseOr().getTree();

                    break;

                case 'A':

                    ast = (CommonTree) this.parser.clauseAnd().getTree();

                    break;
                case 'N':

                    ast = (CommonTree) this.parser.clauseNot().getTree();

                    break;
                default:
                    ast = (CommonTree) this.parser.mainQ().getTree();
                    break;

            }


            // create a stream of tree nodes from AST built by parser
            CommonTreeNodeStream nodes = new CommonTreeNodeStream(ast);
            nodes.setTokenStream(tokens);

           // printTree(ast);

            return ast;


        } catch (RecognitionException e) {
            throw new IllegalStateException("Recognition exception is never thrown, only declared.");
        }

    }

    public String TransformQuery(CommonTree tree,HashMap<String, String> transform){

        TreeWalk walk = new TreeWalk(transform);
        walk.Walk(tree);

      return this.tokens.toString(); // emit tweaked token buffer
    }

}
