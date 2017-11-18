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
public class Ecosistema
{

    private ArrayList<Especie> especies;
    private ArrayList<Integer> poblacion;
    private int bioma;
    private ArrayList<ArrayList<Float>> matrizCrecimiento;

    /*BIOMAS:
    * Agua Salada
    * Agua Dulce
    * Llanura
    * Bosque
    * Jungla
    * Desierto
    * Tundra
    * Montaña
     */
    public Ecosistema(int num, ArrayList<Especie> especies)
    {
        this.especies = especies;
        poblacion = new ArrayList();
        this.bioma = num;
        matrizCrecimiento = new ArrayList();
    }

    /*
    *	Funcion para recrear toda la matriz
     */
    void CrearMatriz()
    {
        matrizCrecimiento = new ArrayList();
        int sizeN = especies.size();
        for (int i = 0; i < sizeN; i++) {
            matrizCrecimiento.add(i, new ArrayList());
            for (int j = 0; j < sizeN; j++) {
                if (i == j) {
                    matrizCrecimiento.get(i).add(especies.get(i).GetCrecimiento(bioma));
                    matrizCrecimiento.get(i).set(j, (float) (Math.round(matrizCrecimiento.get(i).get(j) * 1000d) / 1000d));
                } else {
                    matrizCrecimiento.get(i).add(especies.get(i).Depredar(especies.get(j)));
                    matrizCrecimiento.get(i).set(j, (float) (Math.round(matrizCrecimiento.get(i).get(j) * 1000d) / 1000d));
                }
            }
        }
    }

    /*
    *	Funcion para recrear toda la matriz al modificar la especie en posicion ind
     */
    void ModMatriz(int ind)
    {
        int sizeN = especies.size();
        for (int j = 0; j < sizeN; j++) {
            if (ind == j) {
                matrizCrecimiento.get(j).set(ind, especies.get(j).GetCrecimiento(bioma));
                matrizCrecimiento.get(j).set(ind, (float) (Math.round(matrizCrecimiento.get(j).get(ind) * 1000d) / 1000d));
            } else {
                matrizCrecimiento.get(j).set(ind, especies.get(j).Depredar(especies.get(ind)));
                matrizCrecimiento.get(j).set(ind, (float) (Math.round(matrizCrecimiento.get(j).get(ind) * 1000d) / 1000d));
            }
        }
        for (int j = 0; j < sizeN; j++) {
            if (ind != j) {
                matrizCrecimiento.get(ind).set(j, especies.get(ind).Depredar(especies.get(j)));
                matrizCrecimiento.get(ind).set(j, (float) (Math.round(matrizCrecimiento.get(ind).get(j) * 1000d) / 1000d));
            }
        }
    }

    /*
    *	Funcion para añadir las especies no creadas a la matriz de crecimiento de población
     */
    void AmpliarMatriz()
    {
        int sizeV = matrizCrecimiento.size();
        int sizeN = especies.size();
        for (int i = 0; i < sizeV; i++) {
            for (int j = sizeV; j < sizeN; j++) {
                matrizCrecimiento.get(i).add(especies.get(i).Depredar(especies.get(j)));
                matrizCrecimiento.get(i).set(j, (float) (Math.round(matrizCrecimiento.get(i).get(j) * 1000d) / 1000d));
            }
        }
        for (int i = sizeV; i < sizeN; i++) {
            matrizCrecimiento.add(new ArrayList());
            for (int j = 0; j < sizeN; j++) {
                if (i == j) {
                    matrizCrecimiento.get(i).add(especies.get(i).GetCrecimiento(bioma));
                    matrizCrecimiento.get(i).set(j, (float) (Math.round(matrizCrecimiento.get(i).get(j) * 1000d) / 1000d));
                } else {
                    matrizCrecimiento.get(i).add(especies.get(i).Depredar(especies.get(j)));
                    matrizCrecimiento.get(i).set(j, (float) (Math.round(matrizCrecimiento.get(i).get(j) * 1000d) / 1000d));
                }
            }
        }
    }

    /*
    *	Calculo de cuantos animales depreda una especie
     */
    private int GetDepredados(int i)
    {
        int sum = 0;
        for (int j = 0; j < matrizCrecimiento.size(); j++) {
            if (matrizCrecimiento.get(i).get(j) != 0 && i != j) {
                sum++;
            }
        }
        return sum;
    }

    /*
    * Funcion para que si una especie ha consumido demasiado, eliminar los miembros que se han alimentado en exceso
     */
    private void reducirExceso(int depredador, float alimentoQuitar, ArrayList<Integer> poblacion)
    {
        float pob = poblacion.get(depredador) + (alimentoQuitar / especies.get(depredador).getAlimRequerido());
        if (pob > 0) {
            poblacion.set(depredador, Math.round(pob));
        } else {
            poblacion.set(depredador, 0);
        }
    }

    /*
    *	Producto de vector por matriz con la particularidad de que si el resultado es menor que cero, ajusta la otra poblacion y no opera si la columna o fila tiene poblacion 0
    *	Ademas, previamente a encontrarse a si mismo, calcula sobre la poblacion anterior si se ha consumido demasiado, y luego al encontrarse a si mismo, elimina esa diferencia
     */
    private ArrayList<Integer> producto(ArrayList<Integer> vector, ArrayList<ArrayList<Float>> matriz)
    {
        float sum;
        int size = vector.size();
        ArrayList<Integer> depredados = new ArrayList();
        for (int i = 0; i < size; i++) {
            depredados.add(GetDepredados(i));
        }
        for (int i = 0; i < size; i++) {
            if (vector.get(i) > 0) {
                sum = vector.get(i);
                for (int j = 0; j < size; j++) {
                    if (vector.get(j) > 0) {
                        if (i == j) {
                            sum -= vector.get(i);
                            sum += (float) (vector.get(i)) * matriz.get(i).get(j);
                            if (sum < 0) {
                                sum = 0;
                            }
                        } else {
                            sum += (float) (vector.get(i)) * matriz.get(j).get(i);
                            if (sum < 0) {
                                reducirExceso(j, sum / (especies.get(i).getAlimDado(true) * depredados.get(j)), vector); //Si depredados es 0 esto no es posible
                                sum = 0;
                            }
                        }
                    }
                    vector.set(i, Math.round(sum));
                }
            }
        }

        return vector;
    }

    /*
    * Funcion para aplicar un ciclo sobre la población, luego comprueba si una especie se ha quedado sin alimento
     */
    ArrayList<Integer> EjecutarCiclo()
    {
        ArrayList<Integer> resultado = producto(poblacion, matrizCrecimiento);
        int size = poblacion.size();
        int depredados;
        for (int i = 0; i < size; i++) {
            if (!especies.get(i).getFlagsAutotrofo()) {
                depredados = 0;
                for (int j = 0; j < size; j++) {
                    if (matrizCrecimiento.get(i).get(j) != 0 && i != j && poblacion.get(j) > 0) {
                        depredados++;
                        j = size;
                    }
                }
                if (depredados == 0) {
                    poblacion.set(i, 0);
                }
            }
        }
        return resultado;
    }

    /*
    *   Devuelve la valoración de esa especie en ese ecosistema
     */
    public int getPuntuacionEspecie(Especie esp)
    {
        for (int i = 0; i < especies.size(); i++) {
            if (especies.get(i) == esp) {
                return esp.getPuntuacion(poblacion.get(i));
            }
        }
        return 0;
    }

    void anadirPoblacion(Especie esp)
    {
        poblacion.add(esp.getPoblacionInicial(bioma));
    }

    @Override
    public String toString()
    {
        String aux = "\nBioma: ";
        switch (bioma) {
            case 0:
                aux += "Agua salada";
                break;
            case 1:
                aux += "Agua dulce";
                break;
            case 2:
                aux += "Llanura";
                break;
            case 3:
                aux += "Bosque";
                break;
            case 4:
                aux += "Jungla";
                break;
            case 5:
                aux += "Desierto";
                break;
            case 6:
                aux += "Tundra";
                break;
            case 7:
                aux += "Montaña";
                break;
        }
        aux += "\n" + poblacion + "\n\n";
        for (int i = 0; i < matrizCrecimiento.size(); i++) {
            aux += matrizCrecimiento.get(i) + "\n";
        }
        return aux;
    }
}
