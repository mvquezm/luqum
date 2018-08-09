package linos.jluqum.parsers;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.TokenStream;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.RecognitionException;

/**
 * Created by jorge.hernandez on 09/08/2018.
 */
public class LuceneCompiler extends AbstractCompiler {

    public CommonTree compile(String expression) {
        try {
            //lexer splits input into tokens
            ANTLRStringStream input = new ANTLRStringStream(expression);
            TokenStream tokens = new CommonTokenStream(new LuceneGrammarLexer(input));

            //parser generates abstract syntax tree
            LuceneGrammarParser  parser = new LuceneGrammarParser (tokens);
            LuceneGrammarParser.mainQ_return ret = parser.mainQ();

            //acquire parse result
            CommonTree ast = (CommonTree) ret.tree;
            printTree(ast);
            return ast;
        } catch (RecognitionException e) {
            throw new IllegalStateException("Recognition exception is never thrown, only declared.");
        }

    }
}
