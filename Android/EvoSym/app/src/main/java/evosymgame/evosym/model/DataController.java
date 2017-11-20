package evosymgame.evosym.model;

import java.util.ArrayList;

/**
 * Created by Jorge on 20/11/2017.
 */

public class DataController {
    private DatabaseConnector databaseConnector;

    public DataController(){
        databaseConnector = new DatabaseConnector();
    }

    public void recoverAbilities (){
        ArrayList<MapMod> data = databaseConnector.retrieveData(Constants.CollectionMejora);
        ArrayList<Mejora> mejoras = new ArrayList<>();
        for(MapMod d : data){
            mejoras.add((Mejora) d.map);
        }
        Mejoras m = new Mejoras();
        m.setLista(mejoras);
    }

    public void printAbilities(){
        ArrayList<Mejora> mejoras = (new Mejoras().GetTodo());

        for(Object m : mejoras){
            if(!databaseConnector.contains(m,Constants.CollectionMejora)){

            }
        }
    }
}
