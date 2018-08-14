package linos.jluqum.observerInterface;

import linos.jluqum.transformer.Model;
import org.antlr.runtime.Token;

import java.util.Observer;


public interface TreeObserver extends Observer {

    public void setModel(Model model);

    public Model getModel();

    public Token getToken();

    public void setToken(Token token);

}
