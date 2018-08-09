package linos.jluqum.parsers;

import org.antlr.runtime.tree.CommonTree;
import org.junit.Test;

/**
 * Created by jorge.hernandez on 09/08/2018.
 */
public class LuceneCompilerTest {

    @Test
    public void testCorrectExpression() {
        //compile the expression
        LuceneCompiler compiler = new LuceneCompiler();
        CommonTree ast = compiler.compile("field1:(+term1 -term2) OR ( (+term3 -term4) NOT field1:(\"phrase one\" +term3))");


    }
}
