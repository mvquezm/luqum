package linos.jluqum.transformer;

import org.javatuples.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jorge.hernandez on 13/08/2018.
 */
public class Model<S,T> {
    private List<Pair<S,T>>mostSimilar;

    public Model() {

    }


   public List<Pair<S,T>> similarByVector(List<Pair<S,T>> vector){
       return getMostSimilar();

   }

    public List<Pair<S,T>>  getNumpyVector(String word){

       return new ArrayList<Pair<S,T>>();

    }

    public List<Pair<S,T>> getMostSimilar() {
        return mostSimilar;
    }

    public void setMostSimilar(List<Pair<S,T>> mostSimilar) {
        this.mostSimilar = mostSimilar;
    }


}
