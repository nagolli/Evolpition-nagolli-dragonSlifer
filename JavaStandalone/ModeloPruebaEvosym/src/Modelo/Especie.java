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
        if(getFlagsAutotrofo())
        {
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

    int getPuntuacion(int poblacion)
    {
        return (int)(poblacion * calculaPuntuacionPorSer());
    }

    int getPoblacionInicial(int bioma)
    {
        if(Entorno.get(bioma)<=0)
            return 0;
        return (int)((10000 + (puntos_rest * 100)) / calculaPuntuacionPorSer());
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
       if(Entorno.get(bioma)<=0)
            return 0;
        else
            return ((float) probReproduccion * (float) camada / 100f + 1f - 1f / (float)((Entorno.get(bioma) + 1) * (Entorno.get(bioma) + 1) * (Entorno.get(bioma) + 1)));
    }

    /*
*	Funcion que calcula la tasa de depredacion de una especie sobre otra, un valor positivo es que en vez de depredar, beneficia
     */
    float Depredar(Especie e2)
    {
        if (this.getFlagsSimbiosis(e2)) {
            return (float) ( (float)(e2.alimDadoB)* (float)(e2.crecB)/100 / this.alimRequerido);
        }
        /*
        System.out.println(nombre+" a "+e2.nombre);
        System.out.println("Bono por tamano:"+this.getVariacionTamano(e2));
        System.out.println("Bono por combate:"+(this.combate + this.caza ));
        System.out.println("Penal por defensa:"+(e2.combate + e2.defensa));
        System.out.println("Otros:"+(this.getFlagsCazaManada(e2)));
        */
        if (this.getFlagsDepredar(e2) && (this.getVariacionTamano(e2) + this.combate - e2.combate + this.caza - e2.defensa + this.getFlagsCazaManada(e2)) > 0) {
            return (float)(-this.alimRequerido) / (float)(e2.alimDado);
        }
        return 0;
    }

    private int getVariacionTamano(Especie e2)
    {
       int aux=0;
       if(this.getFlag(13))
           if((this.getTamanoN()-e2.getTamanoN())==1)
               return 5;
       if(this.getFlag(15))
           if((this.getTamanoN()-e2.getTamanoN())<-1)
               return 5*(e2.getTamanoN()-this.getTamanoN())-5;
       aux+= -Math.abs(5 * (this.tamano - e2.tamano));
       return aux;
    }
    
    /*
    *   Getters de Flags
     */
    private boolean getFlagsSimbiosis() //Tiene alguna simbiosis?
    {
        if(Flags.get(3)||Flags.get(6))
            return true;
        return false;
    }

    private boolean getFlagsDepredar(Especie e2) //Esta especie depreda a la otra
    {
        if(Flags.get(9)&&e2.getFlag(1))
            return true;
        if(Flags.get(8)&&e2.getFlag(2))
            return true;
        return false;
    }
    boolean getFlagsAutotrofo() //Esta especie sobrevive por si misma?
    {
        if(Flags.get(14))
            return true;
        return false;
    }

    private boolean getFlagsSimbiosis(Especie e2) //Tiene simbiosis con e2?
    {
        if(Flags.get(4)&&e2.getFlag(3))
            return true;
        if(Flags.get(7)&&e2.getFlag(6))
            return true;
        return false;
    }

    private boolean getFlagsCazador() //Esta especie se alimenta de animales
    {
        if(Flags.get(8))
            return true;
        return false;
    }
    
    private int getFlagsCazaManada(Especie e2) //Esta especie se alimenta de animales
    {
        if(Flags.get(12)&&e2.getFlag(11))
            return 3;
        return 0;
    }

    private boolean getFlagsHervivoro() //Esta especie se alimenta de plantas
    {
        if(Flags.get(9))
            return true;
        return false;
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

    public float getCamada()
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
            case 3:
                return "Muy Pequeño";
            case 6:
                return "Grande";
        }
        return "";
    }

    public int getCrecB()
    {
        return crecB;
    }
    
    private Boolean getFlag(int i)
    {
        return Flags.get(i);
    }

        private int getTamanoN()
    {
        return tamano;
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

    public void addCamada(float camada)
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


    @Override
    public String toString()
    {
        return this.nombre;
    }

    


}
