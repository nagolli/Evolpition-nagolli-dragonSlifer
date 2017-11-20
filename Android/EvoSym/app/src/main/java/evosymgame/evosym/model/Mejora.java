/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evosymgame.evosym.model;

import java.util.ArrayList;

/**
 * Clase mejora
 * @author Ignacio
 */
public final class Mejora
{
    private final int id;
    private final String nombre;
    private final int flagRequerida;
    private final int coste;
    private final int flag;

    private final int probReproduccion;
    private final float camada;

    private final int combate;
    private final int caza;
    private final int defensa;

    private final int tamano;
    private final int alimRequerido;
    private final int alimDado;
    private final int alimDadoB;
    private final int crecB;

    private final int maximoVeces;
    private final int costeCompra;

    private final ArrayList<Integer> Entorno;

    /**
     * Crea una mejora
     * @param id identificador, debería dar un orden a las mejoras
     * @param maximoVeces Maximo de veces que esta mejora puede adquirirse
     * @param flagRequerida Flag requerida para poder ponerse esta mejora
     * @param coste Coste en puntos de esta mejora
     * @param flag Flag aportado de esta mejora
     * @param probReproduccion Aumento de probabilidad de reproduccion: 1 representa un 1% más
     * @param camada Cantidad de crias adicionales por camada, float
     * @param combate Modificador de combate
     * @param caza Modificador de caza
     * @param defensa Modificador de defensa
     * @param tamano Tamaño que pone esta mejora, no son acumulables en especies
     * @param alimRequerido Modificador de alimento requerido
     * @param alimDado Modificador de alimento dado al ser consumido
     * @param alimDadoB Modificador de alimento dado en simbiosis
     * @param crecB  Modificador de probabilidad de reproduccion por simbiosis
     * @param adapsalada  Modificador de entorno para el bioma agua salada
     * @param adapdulce Modificador de entorno para el bioma agua dulce
     * @param adapllanura Modificador de entorno para el bioma llanura
     * @param adapbosque Modificador de entorno para el bioma bosque
     * @param adapjungla Modificador de entorno para el bioma selva
     * @param adapdesierto Modificador de entorno para el bioma desierto
     * @param adaptundra Modificador de entorno para el bioma tundra
     * @param adapmontaña Modificador de entorno para el bioma montaña
     * @param nombre Nombre de la mejora
     * @param costeCompra Coste para que un jugador adquiera esta mejora para sus especies
     */
    public Mejora(int id, int maximoVeces, int flagRequerida, int coste, int flag, int probReproduccion, float camada, int combate, int caza, int defensa, int tamano, int alimRequerido, int alimDado, int alimDadoB, int crecB, int adapsalada, int adapdulce, int adapllanura, int adapbosque, int adapjungla, int adapdesierto, int adaptundra, int adapmontaña, int costeCompra, String nombre)
    {
        this.maximoVeces = maximoVeces;
        this.id = id;
        this.nombre = nombre;
        this.coste = coste;
        this.flag = flag;
        this.flagRequerida = flagRequerida;
        this.probReproduccion = probReproduccion;
        this.camada = camada;
        this.combate = combate;
        this.caza = caza;
        this.defensa = defensa;
        this.tamano = tamano;
        this.alimRequerido = alimRequerido;
        this.alimDado = alimDado;
        this.alimDadoB = alimDadoB;
        this.crecB = crecB;
        this.Entorno = new ArrayList();
        this.Entorno.add(adapsalada);
        this.Entorno.add(adapdulce);
        this.Entorno.add(adapllanura);
        this.Entorno.add(adapbosque);
        this.Entorno.add(adapjungla);
        this.Entorno.add(adapdesierto);
        this.Entorno.add(adaptundra);
        this.Entorno.add(adapmontaña);
        this.costeCompra=costeCompra;
    }

    /**
     * Añade a la especie los datos de esta mejora
     * @param esp especie a la cual se le añaden los datos
     * @return Falso si no se podia aplicar esta mejora
     */
    boolean sumarDatos(Especie esp)
    {
        if (!this.Adquirible(esp)) {
            return false;
        }
        esp.RestPuntosRest(coste);
        if (tamano > 0) {
            esp.setTamano(tamano);
        }
        esp.addAlimDado(alimDado);
        esp.addAlimDadoB(alimDadoB);
        esp.addAlimRequerido(alimRequerido);
        esp.addCamada(camada);
        esp.addProbReproduccion(probReproduccion);
        esp.addCaza(caza);
        esp.addCombate(combate);
        esp.addDefensa(defensa);
        esp.addCrecB(crecB);
        for (int i = 0; i < Entorno.size(); i++) {
            esp.addEntorno(i, Entorno.get(i));
        }
        if (flag > 0) {
            esp.setFlag(flag, true);
        }
        return true;
    }

    /**
     * Quita a la especie los datos de esta mejora
     * @param esp especie a la cual se le añaden los datos
     */
    public void restarDatos(Especie esp)
    {
        esp.RestPuntosRest(-coste);
        if (tamano > 0) {
            esp.setTamano(5);   //TAMAÑO MEDIANO
        }
        esp.addAlimDado(-alimDado);
        esp.addAlimDadoB(-alimDadoB);
        esp.addAlimRequerido(-alimRequerido);
        esp.addCamada(-camada);
        esp.addProbReproduccion(-probReproduccion);
        esp.addCaza(-caza);
        esp.addCombate(-combate);
        esp.addDefensa(-defensa);
        esp.addCrecB(-crecB);
        for (int i = 0; i < Entorno.size(); i++) {
            esp.addEntorno(i, -Entorno.get(i));
        }
        //Comprueba si es la ultima mejora que aporta cierto flag
        if (flag > 0) {
            if (esp.contarMejorasConFlag(flag) == 1) {
                esp.setFlag(flag, false);
            }
        }
    }

    /**
     * Calcula si esta mejora es adquirible por una especie
     * @param esp Especie que adquiriría esta mejora
     * @return True si puede adquirirla, false en caso contrario
     */
    boolean Adquirible(Especie esp)
    {
        int puntos=esp.getPuntosRest();
        if (esp.getCantMejora(this) == this.maximoVeces || esp.getPuntosRest() < coste) {
            return false;
        }
        if (flagRequerida > 0) {
            if (!esp.getFlags().get(flagRequerida)) {
                return false;
            }
        }
        if (tamano != 0 && esp.getTamanoN()!=5) {
            return false;
        }
        return (puntos >= coste);
    }

    /**
     * Devuelve el flag que activa esta mejora
     *
     * @return numero de flag de la mejora
     */
    int getFlag()
    {
        return flag;
    }

    /**
     * Devuelve el nombre de la mejora
     *
     * @return nombre de la mejora
     */
    String getNombre()
    {
        return this.nombre;
    }

    /**
     * Devuelve el coste de compra de la mejora
     *
     * @return coste de la mejora para el jugador
     */
    public int getCosteCompra()
    {
        return costeCompra;
    }

    @Override
    public String toString()
    {
        return this.nombre;
    }
}
