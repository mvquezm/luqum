package linos.jluqum.parsers;

import linos.jluqum.transformer.IntentQueryParserTransformer;
import linos.jluqum.transformer.Model;

import org.javatuples.Pair;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LuceneCompilerTest {

    @Test
    public void testCorrectExpression() {
        //compile the expression
        LuceneCompiler compiler = new LuceneCompiler();
        List<String> queries = Arrays.asList(
                "field1:(+term1 -term2) OR ((+term3 -term4) NOT field1:(\"phrase one\" +term3))",
                "field1:(+term1 -term2) OR ((term3 OR term4) NOT field1:(\"phrase one\" OR +term3))"
        );


        List<Pair<String, Double>> tuples = new ArrayList<Pair<String, Double>>();
        tuples.add(new Pair<String, Double>("porsche", 0.9232932932));
        tuples.add(new Pair<String, Double>("porsche_carrera", 0.88882323));

        IntentQueryParserTransformer iqp = new IntentQueryParserTransformer(new Model<String,Double>(tuples));


        for (String query : queries) {
            System.out.println(String.format("Original Query: %s", query));
            compiler.compile(query);
            //compiler.printTree(compiler.TreeForContex('Q'));
            System.out.println(String.format("Transform Query: %s \n",
                    compiler.TransformQuery(compiler.TreeForContex("Query"),iqp)));
        }
    }
}
