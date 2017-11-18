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
public class Planeta
{

    private ArrayList<Ecosistema> ecosistemas;
    private ArrayList<Especie> especies;

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

    public void AddEspecie(Especie esp)
    {
        especies.add(esp);
        for (int i = 0; i < ecosistemas.size(); i++) {
            ecosistemas.get(i).anadirPoblacion(esp);
            ecosistemas.get(i).AmpliarMatriz();
        }
    }

    public void SiguienteTurno()
    {
        for (int i = 0; i < ecosistemas.size(); i++) {
            ecosistemas.get(i).EjecutarCiclo();
        }
    }

    public int GetPuntuacionEspecie(Especie esp)
    {
        int sum = 0;
        for (int i = 0; i < ecosistemas.size(); i++) {
            sum = sum + ecosistemas.get(i).getPuntuacionEspecie(esp);
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
