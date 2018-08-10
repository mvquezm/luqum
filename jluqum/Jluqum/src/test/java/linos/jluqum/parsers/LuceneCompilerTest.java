package linos.jluqum.parsers;

import org.antlr.runtime.tree.CommonTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LuceneCompilerTest {

    @Test
    public void testCorrectExpression() {
        //compile the expression
        LuceneCompiler compiler = new LuceneCompiler();
        List<String> queries = Arrays.asList(
                "field1:(+term1 -term2) OR ( (+term3 -term4) NOT field1:(\"phrase one\" +term3))",
                "field1:(+term1 -term2) OR ( (term3 OR term4) NOT field1:(\"phrase one\" OR +term3) )"
        );

        for (String query: queries) {
            System.out.println(String.format("Query: %s", query));
            CommonTree ast = compiler.compile(query);
        }
    }
}
