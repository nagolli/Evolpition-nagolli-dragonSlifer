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
public class Test
{
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Mejoras Mejoras= new Mejoras();
        ArrayList<Boolean> ecosistemas=new ArrayList();
            ecosistemas.add(false);//AGUA SALADA
            ecosistemas.add(false);//AGUA DULCE
            ecosistemas.add(true);//LLANURA
            ecosistemas.add(false);//BOSQUE
            ecosistemas.add(false);//JUNGLA
            ecosistemas.add(true);//DESIERTO
            ecosistemas.add(true); //TUNDRA
            ecosistemas.add(false); //MONTAÑA
        Planeta p=new Planeta(ecosistemas);
        System.out.println("Planeta creado con exito!");
        Especie cesped = new Especie();
        cesped.setNombre("Cesped");
        cesped.addMejora(Mejoras.GetId(53));
        cesped.addMejora(Mejoras.GetId(21));
        cesped.addMejora(Mejoras.GetId(43));
        cesped.addMejora(Mejoras.GetId(25));
        cesped.addMejora(Mejoras.GetId(39));
        cesped.addMejora(Mejoras.GetId(37));
        cesped.addMejora(Mejoras.GetId(9));
        cesped.addMejora(Mejoras.GetId(9));
        cesped.addMejora(Mejoras.GetId(9));
        cesped.addMejora(Mejoras.GetId(9));
        cesped.addMejora(Mejoras.GetId(9));
        cesped.addMejora(Mejoras.GetId(58));
        cesped.finEditar();
        p.AddEspecie(cesped);
        
        Especie acacia = new Especie();
        acacia.setNombre("Acacia");
        acacia.addMejora(Mejoras.GetId(53));
        acacia.addMejora(Mejoras.GetId(21));
        acacia.addMejora(Mejoras.GetId(24));
        acacia.addMejora(Mejoras.GetId(25));
        acacia.addMejora(Mejoras.GetId(39));
        acacia.addMejora(Mejoras.GetId(29));
        acacia.addMejora(Mejoras.GetId(49));
        acacia.addMejora(Mejoras.GetId(22));
        acacia.addMejora(Mejoras.GetId(38));
        acacia.addMejora(Mejoras.GetId(38));
        acacia.finEditar();
        p.AddEspecie(acacia);
        
        Especie cebra = new Especie();
        cebra.setNombre("Cebra");
        cebra.addMejora(Mejoras.GetId(2));
        cebra.addMejora(Mejoras.GetId(27));
        cebra.addMejora(Mejoras.GetId(49));
        cebra.addMejora(Mejoras.GetId(25));
        cebra.addMejora(Mejoras.GetId(15));
        cebra.addMejora(Mejoras.GetId(6));
        cebra.addMejora(Mejoras.GetId(44));
        cebra.addMejora(Mejoras.GetId(30));
        cebra.addMejora(Mejoras.GetId(1));
        cebra.addMejora(Mejoras.GetId(47));
        cebra.addMejora(Mejoras.GetId(45));
        cebra.addMejora(Mejoras.GetId(14));
        cebra.addMejora(Mejoras.GetId(34));
        cebra.finEditar();
        p.AddEspecie(cebra);
        
        Especie grillo = new Especie();
        grillo.setNombre("Grillo");
        grillo.addMejora(Mejoras.GetId(2));
        grillo.addMejora(Mejoras.GetId(32));
        grillo.addMejora(Mejoras.GetId(27));
        grillo.addMejora(Mejoras.GetId(49));
        grillo.addMejora(Mejoras.GetId(54));
        grillo.addMejora(Mejoras.GetId(13));
        grillo.addMejora(Mejoras.GetId(34));
        grillo.addMejora(Mejoras.GetId(25));
        grillo.addMejora(Mejoras.GetId(40));
        grillo.addMejora(Mejoras.GetId(38));
        grillo.addMejora(Mejoras.GetId(41));
        grillo.addMejora(Mejoras.GetId(48));
        grillo.addMejora(Mejoras.GetId(17));
        grillo.addMejora(Mejoras.GetId(35));
        grillo.addMejora(Mejoras.GetId(57));
        grillo.finEditar();
        p.AddEspecie(grillo);
        
        Especie mono = new Especie();
        mono.setNombre("Mono");
        mono.addMejora(Mejoras.GetId(2));
        mono.addMejora(Mejoras.GetId(43));
        mono.addMejora(Mejoras.GetId(27));
        mono.addMejora(Mejoras.GetId(7));
        mono.addMejora(Mejoras.GetId(50));
        mono.addMejora(Mejoras.GetId(1));
        mono.addMejora(Mejoras.GetId(52));
        mono.addMejora(Mejoras.GetId(10));
        mono.addMejora(Mejoras.GetId(4));
        mono.addMejora(Mejoras.GetId(15));
        mono.addMejora(Mejoras.GetId(26));
        mono.addMejora(Mejoras.GetId(47));
        mono.addMejora(Mejoras.GetId(45));
        mono.addMejora(Mejoras.GetId(20));
        mono.finEditar();
        p.AddEspecie(mono);
        
        Especie leon = new Especie();
        leon.setNombre("Leon");
        leon.addMejora(Mejoras.GetId(2));
        leon.addMejora(Mejoras.GetId(7));
        leon.addMejora(Mejoras.GetId(18));
        leon.addMejora(Mejoras.GetId(23));
        leon.addMejora(Mejoras.GetId(8));
        leon.addMejora(Mejoras.GetId(47));
        leon.addMejora(Mejoras.GetId(5));
        leon.addMejora(Mejoras.GetId(12));
        leon.addMejora(Mejoras.GetId(31));
        leon.addMejora(Mejoras.GetId(15));
        leon.addMejora(Mejoras.GetId(25));
        leon.addMejora(Mejoras.GetId(49));
        leon.addMejora(Mejoras.GetId(30));
        leon.finEditar();
        p.AddEspecie(leon);
        
        Especie jirafa = new Especie();
        jirafa.setNombre("Jirafa");
        jirafa.addMejora(Mejoras.GetId(2));
        jirafa.addMejora(Mejoras.GetId(24));
        jirafa.addMejora(Mejoras.GetId(27));
        jirafa.addMejora(Mejoras.GetId(15));
        jirafa.addMejora(Mejoras.GetId(25));
        jirafa.addMejora(Mejoras.GetId(49));
        jirafa.addMejora(Mejoras.GetId(51));
        jirafa.addMejora(Mejoras.GetId(47));
        jirafa.addMejora(Mejoras.GetId(6));
        jirafa.addMejora(Mejoras.GetId(34));
        jirafa.addMejora(Mejoras.GetId(3));
        jirafa.addMejora(Mejoras.GetId(36));
        jirafa.addMejora(Mejoras.GetId(25));
        jirafa.addMejora(Mejoras.GetId(25));
        jirafa.addMejora(Mejoras.GetId(38));
        jirafa.addMejora(Mejoras.GetId(38));
        jirafa.addMejora(Mejoras.GetId(38));
        jirafa.finEditar();
        p.AddEspecie(jirafa);
        
        Especie elefante = new Especie();
        elefante.setNombre("Elefante");
        elefante.addMejora(Mejoras.GetId(2));
        elefante.addMejora(Mejoras.GetId(24));
        elefante.addMejora(Mejoras.GetId(27));
        elefante.addMejora(Mejoras.GetId(15));
        elefante.addMejora(Mejoras.GetId(25));
        elefante.addMejora(Mejoras.GetId(49));
        elefante.addMejora(Mejoras.GetId(51));
        elefante.addMejora(Mejoras.GetId(47));
        elefante.addMejora(Mejoras.GetId(33));
        elefante.addMejora(Mejoras.GetId(11));
        elefante.addMejora(Mejoras.GetId(30));
        elefante.addMejora(Mejoras.GetId(55));
        elefante.addMejora(Mejoras.GetId(55));
        elefante.finEditar();
        p.AddEspecie(elefante);
        
        System.out.println("Iteracion 0:\n"+p+"\n");
        p.SiguienteTurno();
        System.out.println("Iteracion 1:\n"+p+"\n");
        p.SiguienteTurno();
        System.out.println("Iteracion 2:\n"+p+"\n");

        System.exit(0);
    }
    
}
