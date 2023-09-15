package main;
import java.util.*;

public class Mazzo {
    LinkedList<Carta> listaCarte;
    private final String[] listaSemi = {"bastoni", "coppe", "spade", "denari"};

    public Mazzo(boolean nuovoMazzo){
        this.listaCarte = new LinkedList<Carta>();
        if(nuovoMazzo){ 
            for(String seme : listaSemi) {
			    for(int num = 1; num<= 10; num++) 
				    listaCarte.addLast(new Carta(num,seme));
		    }
            Collections.shuffle(this.listaCarte);
        }
    }

    public LinkedList<Carta> getListaCarte(){
        return this.listaCarte;
    }

    
}
