/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Ignacio
 */
public class Especie
{

    private String nombre;
    private ArrayList<Mejora> Mejoras;	    //Listado de mejoras adquiridas	
    private int puntos_rest;		    //Puntos para gastar en mejoras
    private ArrayList<Boolean> Flags;	    //Marcadores especiales de mejoras //FLAG 0 no se usa

    private int probReproduccion;	//Probabilidad de reproduccion
    private int camada;                 //Aumento de poblacion por cada reproduccion

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
    private int calculaPuntuacionPorSer()
    {
        int alimentacion = 1;
        if (getFlagsCazador()) {
            alimentacion += 2;
        }
        if (getFlagsSimbiosis()) {
            alimentacion += 1;
        }
        if (getFlagsHervivoro()) {
            alimentacion += 1;
        }
        if (getFlagsOmnivoro()) {
            alimentacion -= 3;
        }
        switch (tamano) {
            case 1:
                return alimentacion / 10000;
            case 2:
                return alimentacion / 100;
            case 3:
                return alimentacion / 1;
            default:
                return -10;
        }
    }

    int getPuntuacion(int poblacion)
    {
        return poblacion * calculaPuntuacionPorSer();
    }

    Integer getPoblacionInicial()
    {
        return (10000 + puntos_rest * 100) / calculaPuntuacionPorSer();
    }

    //FUNCIONES PARA CREACION
    public Especie()
    {
        this.puntos_rest = 100;
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
            Entorno.add(0);
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
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public boolean addMejora(Mejora mejora)
    {
        if (mejora.sumarDatos(this)) {
            Mejoras.add(mejora);
            return true;
        }
        return false;
    }

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

    public void finEditar()
    {
        alimRequerido *= tamano;
        alimDado *= tamano;
        alimDadoB *= tamano;
    }
    
    //FUNCIONES PARA MODELO
    /*
    *	Funcion que calcula el desarrollo de la especie en un bioma sin interaccion de otras especies
     */
    float GetCrecimiento(int bioma)
    {
        return ((float) probReproduccion * (float) camada / 100 + 1 - 1 / ((Entorno.get(bioma) + 1) * (Entorno.get(bioma) + 1) * (Entorno.get(bioma) + 1)));
    }

    /*
*	Funcion que calcula la tasa de depredacion de una especie sobre otra, un valor positivo es que en vez de depredar, beneficia
     */
    float Depredar(Especie e2)
    {
        if (this.getFlagsSimbiosis(e2)) {
            return (float) (this.alimRequerido * crecB / e2.alimDadoB);
        }
        if (this.getFlagsDepredar(e2) && (Math.abs(5 * (this.tamano - e2.tamano)) + this.combate - e2.combate + this.caza - e2.defensa) > 0) {
            return (float) (-this.alimRequerido / e2.alimDado);
        }
        return 0;
    }

    /*
    *   Getters de Flags
     */
    private boolean getFlagsSimbiosis() //Tiene alguna simbiosis?
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean getFlagsDepredar(Especie e2) //Esta especie depreda a la otra
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    boolean getFlagsAutotrofo() //Esta especie sobrevive por si misma?
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean getFlagsSimbiosis(Especie e2) //Tiene simbiosis con e2?
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean getFlagsCazador() //Esta especie se alimenta de animales
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean getFlagsHervivoro() //Esta especie se alimenta de plantas
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean getFlagsOmnivoro()
    {
        return (getFlagsCazador() && getFlagsHervivoro());
    }

    /*
    *   Getters estandar
     */
    int getAlimRequerido()
    {
        return this.alimRequerido;
    }

    ArrayList<Boolean> getFlags()
    {
        return this.Flags;
    }

    int getPuntosRest()
    {
        return this.puntos_rest;
    }

    float getAlimDado(boolean caza)
    {
        if (caza) {
            return this.alimDado;
        } else {
            return this.alimDadoB;
        }
    }

    public String getNombre()
    {
        return nombre;
    }

    public int getPuntos_rest()
    {
        return puntos_rest;
    }

    public int getProbReproduccion()
    {
        return probReproduccion;
    }

    public int getCamada()
    {
        return camada;
    }

    public int getCombate()
    {
        return combate;
    }

    public int getCaza()
    {
        return caza;
    }

    public int getDefensa()
    {
        return defensa;
    }

    public String getTamano()
    {
        switch (tamano) {
            case 5:
                return "Mediano";
            case 4:
                return "Pequeño";
            case 6:
                return "Grande";
        }
        return "";
    }

    public int getCrecB()
    {
        return crecB;
    }

    /*
    * Otros getters
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
    public void RestPuntos_rest(int puntos_rest)
    {
        this.puntos_rest -= puntos_rest;
    }

    public void setFlag(int i, boolean valor)
    {
        this.Flags.set(i, valor);
    }

    public void addProbReproduccion(int probReproduccion)
    {
        this.probReproduccion += probReproduccion;
    }

    public void addCamada(int camada)
    {
        this.camada += camada;
    }

    public void addCombate(int combate)
    {
        this.combate += combate;
    }

    public void addCaza(int caza)
    {
        this.caza += caza;
    }

    public void addDefensa(int defensa)
    {
        this.defensa += defensa;
    }

    public void setTamano(int tamano)
    {
        this.tamano = tamano;
    }

    public void addAlimRequerido(int alimRequerido)
    {
        this.alimRequerido += alimRequerido;
    }

    public void addAlimDado(int alimDado)
    {
        this.alimDado += alimDado;
    }

    public void addAlimDadoB(int alimDadoB)
    {
        this.alimDadoB += alimDadoB;
    }

    public void addCrecB(int crecB)
    {
        this.crecB += crecB;
    }

    public void addEntorno(int i, int valor)
    {
        this.Entorno.set(i, valor + this.Entorno.get(i));
    }

}
