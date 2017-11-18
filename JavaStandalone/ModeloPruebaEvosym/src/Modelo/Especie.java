/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 * Clase especie, representa una especie y sus parametros
 * @author Ignacio
 */
public class Especie
{

    private String nombre;
    private ArrayList<Mejora> Mejoras;	    //Listado de mejoras adquiridas	
    private int puntosRest;		    //Puntos para gastar en mejoras
    private ArrayList<Boolean> Flags;	    //Marcadores especiales de mejoras //FLAG 0 no se usa

    private int probReproduccion;	//Probabilidad de reproduccion
    private float camada;                 //Aumento de poblacion por cada reproduccion

    private int combate;		//Valor de combate
    private int caza;                   //Valor de caza
    private int defensa;		//Valor de defensa

    private int tamano;			//Indice de tamano
    private int alimRequerido;          //Indica cuanto alimento necesita
    private int alimDado;		//Indica cuanto alimento da al ser devorado, depende de tamano+Modificadores
    private int alimDadoB;		//Indica cuanto alimento da al dar alimento secundario
    private int crecB;			//Indica cuanto crece por simbiosis

    private final ArrayList<Integer> Entorno;	//A mayor entorno, menor probabilidad de muerte natural en ese bioma// funcion: =1/((x+1)^3)

    //Funciones de equilibrio
    /**
     * Calcula la puntuacion dada por esta especie
     *
     * @return valor
     */
    private float calculaPuntuacionPorSer()
    {
        float alimentacion = 1f;
        if (getFlagsCazador()) {
            alimentacion += 2f;
        }
        if (getFlagsSimbiosis()) {
            alimentacion += 1f;
        }
        if (getFlagsHervivoro()) {
            alimentacion += 1f;
        }
        if (getFlagsOmnivoro()) {
            alimentacion -= 2f;
        }
        if (getFlagsAutotrofo()) {
            alimentacion /= 10f;
        }
        switch (tamano) {
            case 3:
                return alimentacion / 1000;
            case 4:
                return alimentacion / 100;
            case 5:
                return alimentacion / 10;
            case 6:
                return alimentacion / 1;
            default:
                return -10;
        }
    }

    /**
     * Devuelve la puntuación de esta especie dada una población
     *
     * @param poblacion poblacion de la especie
     * @return puntuacion de la especie
     */
    int getPuntuacion(int poblacion)
    {
        return (int) (poblacion * calculaPuntuacionPorSer());
    }

    /**
     * Determina la poblacion inicial de la especie
     *
     * @param bioma si su valor de entorno es 0 la poblacion inicial será 0.
     * @return cantidad inicial
     */
    int getPoblacionInicial(int bioma)
    {
        if (Entorno.get(bioma) <= 0) {
            return 0;
        }
        return (int) ((10000 + (puntosRest * 100)) / calculaPuntuacionPorSer());
    }

    //FUNCIONES PARA CREACION
    /**
     * Inicializa la especie con todas las constantes
     */
    public Especie()
    {
        this.puntosRest = 100;
        this.Entorno = new ArrayList();
        for (int i = 0; i < 8; i++) //CAMBIAR SI SE AÑADEN ECOSISTEMAS, ACTUALMENTE 8
        {
            Entorno.add(0);
        }
        this.Mejoras = new ArrayList();
        this.Flags = new ArrayList();
        Flags.ensureCapacity(256);
        for (int i = 0; i < 256; i++) //CAMBIAR SI SE EXCEDEN, 256 ocupa 1 byte
        {
            Flags.add(false);
        }
        probReproduccion = 5;
        camada = 1;

        combate = 0;
        caza = 0;
        defensa = 0;

        tamano = 5;
        alimRequerido = 10;
        alimDado = 10;
        alimDadoB = 0;
        crecB = 0;
        this.nombre = "UNNAMED" + Math.random() % 10000;
    }

    /**
     * Ajusta el nombre de la especie
     *
     * @param nombre nombre que tendrá la especie
     */
    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    /**
     * Añade una mejora a la especie
     *
     * @param mejora mejora que se le va a añadir
     * @return Falso si no ha podido añadir la mejora
     */
    public boolean addMejora(Mejora mejora)
    {
        if (mejora.sumarDatos(this)) {
            Mejoras.add(mejora);
            return true;
        }
        return false;
    }

    /**
     * Quita una mejora a la especie, solo quita una en caso de haber multiples
     * de la misma
     *
     * @param mejora mejora que se le va a quitar
     * @return Falso si no tiene esa mejora
     */
    public boolean removeMejora(Mejora mejora)
    {
        for (int i = 0; i < Mejoras.size(); i++) {
            if (Mejoras.get(i) == mejora) {
                Mejoras.remove(i);
                mejora.restarDatos(this);
                return true;
            }
        }
        return false;
    }

    /**
     * Aplica funciones finales a la especie, principalmente para ajustar
     * formulas con operaciones finales despues de haber editado
     */
    public void finEditar()
    {
        alimRequerido *= tamano;
        alimDado *= tamano;
        alimDadoB *= tamano;
    }

    //FUNCIONES PARA MODELO
    /**
     * Funcion que calcula el desarrollo de la especie en un bioma sin
     * interaccion de otras especies
     *
     * @param bioma identificador de bioma en el que se encuentra
     * @return valor
     */
    float GetCrecimiento(int bioma)
    {
        if (Entorno.get(bioma) <= 0) {
            return 0;
        } else {
            return ((float) probReproduccion * (float) camada / 100f + 1f - 1f / (float) ((Entorno.get(bioma) + 1) * (Entorno.get(bioma) + 1) * (Entorno.get(bioma) + 1)));
        }
    }

    /**
     * Funcion que calcula la tasa de depredacion de una especie sobre otra
     *
     * @param e2 especie a la cual esta depredando
     * @return valor, si es positivo beneficia a la otra especie
     */
    float Depredar(Especie e2)
    {
        if (this.getFlagsSimbiosis(e2)) {
            return (float) ((float) (e2.alimDadoB) * (float) (e2.crecB) / 100 / this.alimRequerido);
        }
        /*
        System.out.println(nombre+" a "+e2.nombre);
        System.out.println("Bono por tamano:"+this.getVariacionTamano(e2));
        System.out.println("Bono por combate:"+(this.combate + this.caza ));
        System.out.println("Penal por defensa:"+(e2.combate + e2.defensa));
        System.out.println("Otros:"+(this.getFlagsCazaManada(e2)));
         */
        if (this.getFlagsDepredar(e2) && (this.getVariacionTamano(e2) + this.combate - e2.combate + this.caza - e2.defensa + this.getFlagsCazaManada(e2)) > 0) {
            return (float) (-this.alimRequerido) / (float) (e2.alimDado);
        }
        return 0;
    }

    /**
     * Obtiene el valor de diferencia de tamaño entre especies para depredacion
     *
     * @param e2 segunda especie
     * @return valor de dificultad de caza
     */
    private int getVariacionTamano(Especie e2)
    {
        int aux = 0;
        if (this.getFlag(13)) {
            //La presa es menor en excatamente 1?
            if ((this.getTamanoN() - e2.getTamanoN()) == 1) {
                return 5;
            }
        }
        if (this.getFlag(15)) {
            //La presa es al menos dos veces mayor?
            if ((this.getTamanoN() - e2.getTamanoN()) < -1) {
                return 5 * (e2.getTamanoN() - this.getTamanoN()) - 5;
            }
        }
        aux += -Math.abs(5 * (this.tamano - e2.tamano));
        return aux;
    }

    /*
    *   Getters de Flags
     */
    /**
     * Esta especie se beneficia de alguna simbiosis?
     *
     * @return Verdadero si es cierto
     */
    private boolean getFlagsSimbiosis() //Tiene alguna simbiosis?
    {
        if (Flags.get(3) || Flags.get(6)) {
            return true;
        }
        return false;
    }

    /**
     * Esta especie depreda a la segunda?
     *
     * @param e2 especie depredada
     * @return Verdadero si es cierto
     */
    private boolean getFlagsDepredar(Especie e2) //Esta especie depreda a la otra
    {
        //No resiste el veneno y Venenoso
        if (!Flags.get(17) && e2.getFlag(16)) {
            return false;
        }
        //Hervivoro y Vegetal
        if (Flags.get(9) && e2.getFlag(1)) {
            return true;
        }
        //Carnivoro y Animal
        if (Flags.get(8) && e2.getFlag(2)) {
            return true;
        }
        return false;
    }

    /**
     * Esta criatura es autotrofa?
     *
     * @return Verdadero si es cierto
     */
    boolean getFlagsAutotrofo() //Esta especie sobrevive por si misma?
    {
        if (Flags.get(14)) {
            return true;
        }
        return false;
    }

    /**
     * Esta especie se alimenta de una simbiosis de la segunda?
     *
     * @param e2 segunda especie
     * @return Verdadero si es cierto
     */
    private boolean getFlagsSimbiosis(Especie e2) //Tiene simbiosis con e2?
    {
        if (Flags.get(4) && e2.getFlag(3)) {
            return true;
        }
        if (Flags.get(7) && e2.getFlag(6)) {
            return true;
        }
        return false;
    }

    /**
     * Esta especie es cazadora?
     *
     * @return Verdadero si es cierto
     */
    private boolean getFlagsCazador() //Esta especie se alimenta de animales
    {
        if (Flags.get(8)) {
            return true;
        }
        return false;
    }

    /**
     * Esta especie es buena cazando manadas y la depredada forma manadas?
     *
     * @param e2 Especie depredada
     * @return Verdadero si es cierto
     */
    private int getFlagsCazaManada(Especie e2) //Esta especie se alimenta de animales
    {
        if (Flags.get(12) && e2.getFlag(11)) {
            return 3;
        }
        return 0;
    }

    /**
     * Esta especie es hervivora?
     *
     * @return Verdadero si es cierto
     */
    private boolean getFlagsHervivoro() //Esta especie se alimenta de plantas
    {
        if (Flags.get(9)) {
            return true;
        }
        return false;
    }

    /**
     * Esta especie es carnivora y hervivora?
     *
     * @return Verdadero si es cierto
     */
    private boolean getFlagsOmnivoro()
    {
        return (getFlagsCazador() && getFlagsHervivoro());
    }

    /*
    *   Getters estandar
     */
    /**
     *
     * @return
     */
    int getAlimRequerido()
    {
        return this.alimRequerido;
    }

    /**
     *
     * @return
     */
    ArrayList<Boolean> getFlags()
    {
        return this.Flags;
    }

    int getPuntosRest()
    {
        return this.puntosRest;
    }

    /**
     *
     * @param caza
     * @return
     */
    float getAlimDado(boolean caza)
    {
        if (caza) {
            return this.alimDado;
        } else {
            return this.alimDadoB;
        }
    }

    /**
     *
     * @return
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     *
     * @return
     */
    public int getProbReproduccion()
    {
        return probReproduccion;
    }

    /**
     *
     * @return
     */
    public float getCamada()
    {
        return camada;
    }

    /**
     *
     * @return
     */
    public int getCombate()
    {
        return combate;
    }

    /**
     *
     * @return
     */
    public int getCaza()
    {
        return caza;
    }

    /**
     *
     * @return
     */
    public int getDefensa()
    {
        return defensa;
    }

    /**
     *
     * @return
     */
    public String getTamanoS()
    {
        switch (tamano) {
            case 5:
                return "Mediano";
            case 4:
                return "Pequeño";
            case 3:
                return "Muy Pequeño";
            case 6:
                return "Grande";
        }
        return "";
    }

    /**
     * Devuelve la probabilidad de criar debido a una simbiosis
     *
     * @return valor
     */
    public int getCrecB()
    {
        return crecB;
    }

    /**
     * Devuelve el estado del flag
     *
     * @param i numero de flag comprobado
     * @return valor del flag
     */
    private Boolean getFlag(int i)
    {
        return Flags.get(i);
    }

    /**
     * Devuelve el valor numerico del tamaño
     *
     * @return valor
     */
    public int getTamanoN()
    {
        return tamano;
    }

    /*
    * Otros getters
     */
    /**
     * Cuenta cuantas mejoras se han adquirido que aporten un flag
     *
     * @param flag numero de flag a contar
     * @return numero de mejoras con ese flag adquiridas
     */
    int contarMejorasConFlag(int flag)
    {
        int cont = 0;
        for (int i = 0; i < Mejoras.size(); i++) {
            if (Mejoras.get(i).getFlag() == flag) {
                cont++;
            }
        }
        return cont;
    }

    /**
     * Devuelve cuantas veces se ha adquirido una mejora
     *
     * @param mejora Mejora a buscar
     * @return cantidad de veces
     */
    int getCantMejora(Mejora mejora)
    {
        int cont = 0;
        for (int i = 0; i < Mejoras.size(); i++) {
            if (Mejoras.get(i) == mejora) {
                cont++;
            }
        }
        return cont;
    }

    /*
    *   Modifiers
     */
    /**
     * Quita los de los puntos restantes la cantidad dada por argumento
     *
     * @param puntos puntos que se restan
     */
    public void RestPuntosRest(int puntos)
    {
        this.puntosRest -= puntos;
    }

    /**
     * Asigna un valor a un flag
     *
     * @param i numero del flag
     * @param valor True para que lo tenga, false para que no lo tenga
     */
    public void setFlag(int i, boolean valor)
    {
        this.Flags.set(i, valor);
    }

    /**
     * Modificador de reproduccion
     *
     * @param probReproduccion modifica la probabilidad de que un ser se
     * reproduzca
     */
    public void addProbReproduccion(int probReproduccion)
    {
        this.probReproduccion += probReproduccion;
    }

    /**
     * Modificador de camada
     *
     * @param camada numero de crias añadidas, puede ser decimal
     */
    public void addCamada(float camada)
    {
        this.camada += camada;
    }

    /**
     * Modificador de combate
     *
     * @param combate combate modificado
     */
    public void addCombate(int combate)
    {
        this.combate += combate;
    }

    /**
     * Modificador de caza
     *
     * @param caza caza modificada
     */
    public void addCaza(int caza)
    {
        this.caza += caza;
    }

    /**
     * Modificador de defensa
     *
     * @param defensa defensa modificada
     */
    public void addDefensa(int defensa)
    {
        this.defensa += defensa;
    }

    /**
     * Asigna un tamaño a una especie
     *
     * @param tamano tamaño que tendrá
     */
    public void setTamano(int tamano)
    {
        this.tamano = tamano;
    }

    /**
     * Modifica cuanto alimento requiere para sobrevivir
     *
     * @param alimRequerido alimento que requiere
     */
    public void addAlimRequerido(int alimRequerido)
    {
        this.alimRequerido += alimRequerido;
    }

    /**
     * Modifica cuanto alimento da cuando es cazadp
     *
     * @param alimDado alimento dado por caza
     */
    public void addAlimDado(int alimDado)
    {
        this.alimDado += alimDado;
    }

    /**
     * Modifica cuanto alimento da por simbiosis
     *
     * @param alimDadoB alimento dado por simbiosis
     */
    public void addAlimDadoB(int alimDadoB)
    {
        this.alimDadoB += alimDadoB;
    }

    /**
     * Modifica el crecimiento por simbiosis
     *
     * @param crecB crecimiento por simbiosis
     */
    public void addCrecB(int crecB)
    {
        this.crecB += crecB;
    }

    /**
     * Modifica la adaptación al bioma
     *
     * @param bioma indicador de bioma modificado
     * @param valor cantidad en que el bioma se modifica
     */
    public void addEntorno(int bioma, int valor)
    {
        this.Entorno.set(bioma, valor + this.Entorno.get(bioma));
    }

    @Override
    public String toString()
    {
        return this.nombre;
    }

}
