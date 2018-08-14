package linos.jluqum.transformer;

import linos.jluqum.observerInterface.TreeObserver;
import org.antlr.runtime.Token;
import org.javatuples.Pair;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;


public class IntentQueryParserTransformer implements TreeObserver {

    private Model model;
    private Token token;

    public IntentQueryParserTransformer() {}

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void update(Observable o, Object arg) {
        this.setToken((Token) arg);
        transformTest();
    }

    public void transformTest() {
        double confidence = 0.1;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.#",simbolos);
        List<Pair<String, Double>> tempTuples = new ArrayList<Pair<String, Double>>();
        StringBuilder sb = new StringBuilder();
        sb.append('(');

        for (Pair<String, Double> tuple : (List<Pair<String, Double>>)model.getMostSimilar()) {
            if (tuple.getValue0().contains("_")) {
                tempTuples.add(new Pair<String, Double>('"' + tuple.getValue0().replace("_", " ") + '"',
                        tuple.getValue1()));

                String[] splitTuple = tuple.getValue0().split("_");
                for (int i = 1; i < splitTuple.length; i++) {
                    tempTuples.add(new Pair<String, Double>(splitTuple[1], tuple.getValue1() / 2));
                }

            } else {
                tempTuples.add(tuple);
            }
        }

        for (Pair<String, Double> tuple : tempTuples) {
            if (tuple.getValue1() >= confidence) {
                sb.append(tuple.getValue0() + '^' + df.format(tuple.getValue1()) + " ");
            }
        }

        sb.setLength(sb.length() - 1);
        sb.append(')');
        token.setText(sb.toString());
    }
}
