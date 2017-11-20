package evosymgame.evosym.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 *
 * @author Ignacio
 * 
 * El planeta es el contenedor de todos los ecosistemas y especies, a la hora de jugar esta clase es con la que se interactua
 * 
 */
public class Planeta implements Parcelable {

    private ArrayList<Ecosistema> ecosistemas;
    private ArrayList<Especie> especies;

    /**
     * Crea los ecosistemas e inicializa el vector de especies.
     * El orden de ecosistemas es: 
     * -Agua Salada
     * -Agua Dulce
     * -Llanura
     * -Bosque
     * -Jungla
     * -Desierto
     * -Tundra
     * -Montaña
     * @param Ecosistemas Array booleano con presencia de cada ecosistema
     */
    public Planeta(ArrayList<Boolean> Ecosistemas)
    {
        especies = new ArrayList();
        this.ecosistemas = new ArrayList();
        for (int i = 0; i < Ecosistemas.size(); i++) {
            if (Ecosistemas.get(i)) {
                this.ecosistemas.add(new Ecosistema(i, especies));
            }
        }
    }

    protected Planeta(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Planeta> CREATOR = new Creator<Planeta>() {
        @Override
        public Planeta createFromParcel(Parcel in) {
            return new Planeta(in);
        }

        @Override
        public Planeta[] newArray(int size) {
            return new Planeta[size];
        }
    };

    /**
     * Añade una especie al planeta, modificando las matrices de poblacion de todos los ecosistemas y inicializando las poblaciones de los ecosistemas donde vaya a habitar
     * @param especieNueva especie añadida al planeta
     */
    public void AddEspecie(Especie especieNueva)
    {
        especies.add(especieNueva);
        for (int i = 0; i < ecosistemas.size(); i++) {
            ecosistemas.get(i).anadirPoblacion(especieNueva);
            ecosistemas.get(i).AmpliarMatriz();
        }
    }
    
    /**
     * Ejecuta una iteración en el planeta modificando las poblaciones de todos los ecosistemas
     */
    public void SiguienteTurno()
    {
        for (int i = 0; i < ecosistemas.size(); i++) {
            ecosistemas.get(i).EjecutarCiclo();
        }
    }
    
    /**
     * Obtiene la puntuacion de una especie en cada ecosistema y los suma
     * @param especie especie de la cual se obtiene la puntuacion
     * @return entero indicando la puntuacion
     */
    public int GetPuntuacionEspecie(Especie especie)
    {
        int sum = 0;
        for (int i = 0; i < ecosistemas.size(); i++) {
            sum = sum + ecosistemas.get(i).getPuntuacionEspecie(especie);
        }
        return sum;
    }
    
    
    
    @Override
    public String toString()
    {
        String aux =""; 
        for(int i=0;i<especies.size();i++)
        {
            aux += especies.get(i)+"\n";
        }
        for(int i=0;i<ecosistemas.size();i++)
        {
            aux += ecosistemas.get(i)+"\n";
        }
        return aux;
    }
}
