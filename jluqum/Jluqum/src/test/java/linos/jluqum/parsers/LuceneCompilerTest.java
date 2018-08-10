package linos.jluqum.parsers;

import org.antlr.runtime.tree.CommonTree;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
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



        HashMap<String, String> transform = new HashMap<String, String>();

        transform.put("term1","jorge");
        transform.put("term2","Luis");
        transform.put("term3","Hernandez");
        transform.put("term4","Cruz");

        for (String query: queries) {
            System.out.println(String.format("Original Query: %s", query));
            compiler.compile(query);
            System.out.println(String.format("Transform Query: %s",compiler.TransformQuery(compiler.TreeForContex('Q'),transform)));
        }
    }
}
