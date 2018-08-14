package linos.jluqum.transformer;

import org.javatuples.Pair;

import java.util.List;


public class Model<S,T> {
    private List<Pair<S,T>>mostSimilar;

    public Model() {

    }

    public List<Pair<S,T>> getMostSimilar() {
        return mostSimilar;
    }

    public void setMostSimilar(List<Pair<S,T>> mostSimilar) {
        this.mostSimilar = mostSimilar;
    }


}
