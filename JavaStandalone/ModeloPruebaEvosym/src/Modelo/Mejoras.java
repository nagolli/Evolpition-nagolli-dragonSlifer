/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 * Clase contenedora de todas las mejoras En un futuro será sustituida por un
 * XML o JSON
 *
 * @author Ignacio
 */
public class Mejoras
{

    private ArrayList<Mejora> lista;

    /**
     * Devuelve todas las mejoras
     *
     * @return Array con todas las mejoras
     */
    public ArrayList<Mejora> GetTodo()
    {
        return lista;
    }

    /**
     * Devuelve la mejora con el id pasado
     *
     * @param id Id de mejora, coincide con ID
     * @return Mejora con dicho id
     */
    public Mejora GetId(int id)
    {
        return lista.get(id);
    }

    /*
    public ArrayList<Mejora> Filtro("String")
    ArrayList<Mejora> aux=new ArrayList();
    for(int i=0;i<lista;i++)
        if()
            aux.add(lista.get(i))
    return aux;
     */
    /**
     * Crea todas las mejoras en una lista
     */
    public Mejoras()
    {
        lista = new ArrayList();
        //                           Coste    camada   defensa  alimDado salad       jungla   montaña
        //                     Maximo   Flag     combate     alimReq  CrecB dulce bosque   tundra
        //                   ID    Requiere  Repro   caza  tamano   alimDadoB   llanura  desierto nombre
        lista.add(new Mejora(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "NO DEBERIA APARECER NUNCA"));
        lista.add(new Mejora(1, 1, 0, 5, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Alimentarse de cosas pequeñas"));
        lista.add(new Mejora(2, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Animal"));
        lista.add(new Mejora(3, 3, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Bastante delgado"));
        lista.add(new Mejora(4, 1, 0, 13, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, "Bipedo"));
        lista.add(new Mejora(5, 3, 5, 10, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Camadas grandes"));
        lista.add(new Mejora(6, 3, 0, 5, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Camuflaje"));
        lista.add(new Mejora(7, 1, 2, 5, 8, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Carnivoro"));
        lista.add(new Mejora(8, 1, 0, 10, 12, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Caza en grupo a manadas"));
        lista.add(new Mejora(9, 5, 0, 5, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Ciclo reproductivo rápido"));
        lista.add(new Mejora(10, 1, 0, 11, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, "Cola de equilibrio"));
        lista.add(new Mejora(11, 2, 2, 10, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Colmillos"));
        lista.add(new Mejora(12, 1, 8, -1, 0, -15, 0, 1, 0, 0, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Comerse crias"));
        lista.add(new Mejora(13, 1, 0, 10, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Cortejo reproductivo"));
        lista.add(new Mejora(14, 3, 0, 10, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Crecimiento rápido"));
        lista.add(new Mejora(15, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -3, -1, 2, 0, 0, 1, 1, 1, "Cuadrupedo "));
        lista.add(new Mejora(16, 3, 0, 10, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Dentadura potente"));
        lista.add(new Mejora(17, 3, 10, 10, 0, -10, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Desove Masivo"));
        lista.add(new Mejora(18, 1, 0, 10, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Dientes afilados"));
        lista.add(new Mejora(19, 3, 1, 5, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Espinas"));
        lista.add(new Mejora(20, 1, 2, 7, 0, 5, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Familia"));
        lista.add(new Mejora(21, 1, 1, 50, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Fotosintesis"));
        lista.add(new Mejora(22, 1, 1, 3, 6, 2, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, "Frutal"));
        lista.add(new Mejora(23, 2, 2, 10, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Garras"));
        lista.add(new Mejora(24, 1, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Grande"));
        lista.add(new Mejora(25, 3, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, -1, -1, 2, 2, -1, "Grandes extensiones"));
        lista.add(new Mejora(26, 1, 2, 26, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, "Herramientas"));
        lista.add(new Mejora(27, 1, 2, 5, 9, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Hervivoro"));
        lista.add(new Mejora(28, 3, 1, 17, 3, 5, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1, 1, 0, 0, 1, 1, "Hoja Caduca"));
        lista.add(new Mejora(29, 3, 1, 17, 3, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 1, 1, 1, 1, 0, 0, "Hoja Perenne"));
        lista.add(new Mejora(30, 1, 2, 15, 11, 5, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Manada"));
        lista.add(new Mejora(31, 3, 0, -1, 0, 0, 0, 0, 0, -1, 0, -2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Metabolismo lento"));
        lista.add(new Mejora(32, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Muy Pequeño"));
        lista.add(new Mejora(33, 3, 0, -2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Necesita mucho alimento"));
        lista.add(new Mejora(34, 3, 0, 2, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Necesita Poco alimento"));
        lista.add(new Mejora(35, 1, 10, 10, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Nido"));
        lista.add(new Mejora(36, 1, 2, -27, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -6, -3, 0, 0, 0, 0, 0, 0, "No nada"));
        lista.add(new Mejora(37, 3, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, 0, 0, "No resiste el calor "));
        lista.add(new Mejora(38, 3, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, "No resiste el frio "));
        lista.add(new Mejora(39, 3, 0, -6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, "No resiste temperaturas extremas"));
        lista.add(new Mejora(40, 3, 2, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, "Ocultarse en la maleza "));
        lista.add(new Mejora(41, 1, 2, 10, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Ojo Compuesto"));
        lista.add(new Mejora(42, 1, 2, 10, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Ojo de larga distancia"));
        lista.add(new Mejora(43, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Pequeño"));
        lista.add(new Mejora(44, 1, 2, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, "Pezuñas"));
        lista.add(new Mejora(45, 2, 5, 2, 0, 0, 0.5f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Posibles gemelos"));
        lista.add(new Mejora(46, 2, 1, 11, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, "Raices Largas"));
        lista.add(new Mejora(47, 1, 2, 5, 5, 10, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Reproduccion interna"));
        lista.add(new Mejora(48, 1, 2, 0, 10, -10, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Reproduccion por huevos"));
        lista.add(new Mejora(49, 3, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, "Resiste el calor "));
        lista.add(new Mejora(50, 1, 9, 5, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Se alimenta de frutas"));
        lista.add(new Mejora(51, 1, 9, 5, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Se alimenta de hojas"));
        lista.add(new Mejora(52, 3, 2, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 1, "Trepar"));
        lista.add(new Mejora(53, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Vegetal"));
        lista.add(new Mejora(54, 1, 2, 10, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Volar "));
        lista.add(new Mejora(55, 3, 0, 7, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Voluminoso"));
        lista.add(new Mejora(56, 3, 1, 8, 0, 5, 0, 0, 0, 1, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Zarzas"));
        lista.add(new Mejora(57, 1, 9, 10, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, "Infestar arbol"));
        lista.add(new Mejora(58, 1, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Sacia con facilidad"));
        lista.add(new Mejora(59, 3, 0, 15, 16, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Venenoso"));
        lista.add(new Mejora(60, 1, 0, 5, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Resistente al veneno"));
        lista.add(new Mejora(61, 3, 0, 0, 0, 5, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "Colores llamativos"));
        lista.add(new Mejora(62,1,2,11,0,0,0,0,1,0,0,0,0,0,0,1,1,0,0,0,0,0,0,"Pico Pescador"));
        lista.add(new Mejora(63,1,2,9,0,0,0,0,0,0,0,0,0,0,0,1,2,0,0,0,0,0,0,"Patas palmeadas"));
        lista.add(new Mejora(64,3,2,9,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,1,"Pelaje grueso"));
        lista.add(new Mejora(65,1,2,3,0,0,0,0,0,0,0,0,0,0,0,3,4,0,-1,-1,-2,0,-2,"Nadador"));
        lista.add(new Mejora(66,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-2,-2,2,2,"Necesita frio"));
        lista.add(new Mejora(67,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,2,-2,-2,"Necesita calor"));
        lista.add(new Mejora(68,3,2,3,0,2,0.25f,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"Alimentar a las crias"));
        lista.add(new Mejora(69,1,2,15,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,"Pico rompehuesos"));
        lista.add(new Mejora(70,1,0,7,0,0,0,0,0,1,0,-1,0,0,0,0,0,0,0,0,0,0,0,"Marcar el territorio"));
        lista.add(new Mejora(71,3,0,20,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,1,1,2,"Rugir"));
        lista.add(new Mejora(72,1,0,5,18,0,0,0,0,0,0,0,0,0,0,-4,-4,0,0,0,0,0,0,"Alimentarse en rios"));
    }
}
