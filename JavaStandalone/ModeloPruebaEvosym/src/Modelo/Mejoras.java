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
 * NO HACER FORMAT A ESTA PAGINA
 */
public class Mejoras
{
    ArrayList<Mejora> lista;
    
    public ArrayList<Mejora> GetTodo()
    {
        return lista;
    }
    public Mejora GetId(int i)
    {
        return lista.get(i);
    }
    /*
    public ArrayList<Mejora> Filtro("String")
    ArrayList<Mejora> aux=new ArrayList();
    for(int i=0;i<lista;i++)
        if()
            aux.add(lista.get(i))
    return aux;
    */
    public Mejoras()//                                                                 
    {//                                       Coste    camada   defensa  alimDado salad       jungla   montaña
        lista=new ArrayList();//        Maximo   Flag     combate     alimReq  CrecB dulce bosque   tundra
        //Vegetal Flag 1: Planta     ID    Requiere  Repro   caza  tamano   alimDadoB   llanura  desierto nombre
                lista.add(new Mejora( 1, 1, 0, 0, 1, 0, 0,-2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Vegetal"));
    }
}

