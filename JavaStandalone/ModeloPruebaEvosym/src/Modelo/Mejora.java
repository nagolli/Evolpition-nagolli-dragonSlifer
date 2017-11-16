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
public final class Mejora
{
    private final int id;
    private final String nombre;
    private final int flagRequerida;
    private final int coste;
    private final int flag;

    private final int probReproduccion;
    private final int camada;

    private final int combate;
    private final int caza;
    private final int defensa;

    private final int tamano;
    private final int alimRequerido;
    private final int alimDado;
    private final int alimDadoB;
    private final int crecB;
    
    private final int maximoVeces;

    private final ArrayList<Integer> Entorno;

    public Mejora(int id, int maximoVeces, int flagRequerida, int coste, int flag, int probReproduccion, int camada, int combate, int caza, int defensa, int tamano, int alimRequerido, int alimDado, int alimDadoB, int crecB, int adapsalada, int adapdulce, int adapllanura, int adapbosque, int adapjungla, int adapdesierto, int adaptundra, int adapmontaña, String nombre)
    {
        this.maximoVeces=maximoVeces;
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
    }

    boolean sumarDatos(Especie esp)
    {
        if (esp.getCantMejora(this)==this.maximoVeces || esp.getPuntosRest()<coste)
            return false;
        if (flagRequerida > 0) {
            if (!esp.getFlags().get(flagRequerida)) {
                return false;
            }
        }
        esp.RestPuntos_rest(coste);
        if(tamano>0)
            esp.setTamano(tamano);
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
        if(flag>0)
            esp.setFlag(flag, true);
        return true;
    }

    public void restarDatos(Especie esp)
    {
        esp.RestPuntos_rest(-coste);
        if(tamano>0)
        esp.setTamano(5);   //TAMAÑO MEDIANO
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
        if(flag>0)
            if(esp.contarMejorasConFlag(flag)==1)
            esp.setFlag(flag, false);
    }

    boolean Adquirible(Especie esp,int puntos)
    {
        return (puntos>=coste);
    }
    
    int getFlag()
    {
        return flag;
    }
}
