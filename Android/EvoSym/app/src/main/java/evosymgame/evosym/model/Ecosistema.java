package evosymgame.evosym.model;

import java.util.ArrayList;

/**
 * Clase que representa un ecosistema siguiendo un modelo de: vector de
 * poblacion(iteracion i) * matriz de variacion = vector de poblacion(iteracion
 * i+1)
 *
 * @author Ignacio
 */
public class Ecosistema
{

    private ArrayList<Especie> especies;
    private ArrayList<Integer> poblacion;
    private ArrayList<Integer> variacion;
    private int bioma;
    private ArrayList<ArrayList<Float>> matrizCrecimiento;

    /**
     * Copia el vector de especies e inicializa la poblacion y la matriz de
     * crecimiento
     *
     * @param num Identificador de bioma
     * @param especies Vector de especies
     */
    public Ecosistema(int num, ArrayList<Especie> especies)
    {
        this.especies = especies;
        poblacion = new ArrayList();
        variacion = new ArrayList();
        this.bioma = num;
        matrizCrecimiento = new ArrayList();
    }

    /**
     * Funcion para recrear toda la matriz de crecimiento
     *
     * @deprecated Aun no se ha usado
     */
    void CrearMatriz()
    {
        matrizCrecimiento = new ArrayList();
        int sizeN = especies.size();
        for (int i = 0; i < sizeN; i++) {
            matrizCrecimiento.add(i, new ArrayList());
            for (int j = 0; j < sizeN; j++) {
                if (i == j) {
                    //Calcula el crecimiento propio de la especie
                    matrizCrecimiento.get(i).add(especies.get(i).GetCrecimiento(bioma));
                    //Redondea a 3 decimales:
                    matrizCrecimiento.get(i).set(j, (float) (Math.round(matrizCrecimiento.get(i).get(j) * 1000d) / 1000d));
                } else {
                    //Calcula el valor de interaccion entre especies
                    matrizCrecimiento.get(i).add(especies.get(i).Depredar(especies.get(j)));
                    //Redondea a 3 decimales:
                    matrizCrecimiento.get(i).set(j, (float) (Math.round(matrizCrecimiento.get(i).get(j) * 1000d) / 1000d));
                }
            }
        }
    }

    /**
     * Funcion para recrear la matriz al modificar la especie en posicion ind.
     * Solo modifica las interacciones de este indice
     *
     * @param ind Indice de la especie que ha sido modificada
     * @deprecated Aun no se ha usado
     */
    void ModMatriz(int ind)
    {
        int sizeN = especies.size();
        //Modifica la columna que contiene a esa especie, incluida ella misma
        for (int j = 0; j < sizeN; j++) {
            if (ind == j) {
                matrizCrecimiento.get(j).set(ind, especies.get(j).GetCrecimiento(bioma));
                matrizCrecimiento.get(j).set(ind, (float) (Math.round(matrizCrecimiento.get(j).get(ind) * 1000d) / 1000d));
            } else {
                matrizCrecimiento.get(j).set(ind, especies.get(j).Depredar(especies.get(ind)));
                matrizCrecimiento.get(j).set(ind, (float) (Math.round(matrizCrecimiento.get(j).get(ind) * 1000d) / 1000d));
            }
        }
        //Modifica la fila que contiene a esa especie, sin incluirse a si misma
        for (int j = 0; j < sizeN; j++) {
            if (ind != j) {
                matrizCrecimiento.get(ind).set(j, especies.get(ind).Depredar(especies.get(j)));
                matrizCrecimiento.get(ind).set(j, (float) (Math.round(matrizCrecimiento.get(ind).get(j) * 1000d) / 1000d));
            }
        }
    }

    /**
     * Funcion para añadir las especies todavia no actualizadas en la matriz de
     * crecimiento de población
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

    /**
     * Calculo de cuantos animales depreda una especie. Sirve para repartir el
     * nivel de consumo de la especie dependiendo de cuantos otros seres consuma
     *
     * @param ind Indice de la especie que quiere saberse cuantos animales
     * consume en ese bioma
     * @return Total de criaturas de las que se alimenta, tanto por simbiosis
     * como por consumicion
     */
    private int GetDepredados(int ind)
    {
        int sum = 0;
        for (int j = 0; j < matrizCrecimiento.size(); j++) {
            if (matrizCrecimiento.get(ind).get(j) != 0 && ind != j && poblacion.get(j) > 0) {
                sum++;
            }
        }
        return sum;
    }

    /**
     * Funcion para que si una especie ha consumido demasiado, eliminar los
     * miembros que se han alimentado en exceso. Calculara cuantos miembros no
     * han podido alimentarse y los eliminará. No puede dejar una poblacion
     * menor que 0.
     *
     * @param depredador Indice del depredador al que hay que eliminar poblacion
     * @param alimentoQuitar Cantidad de unidades de alimento que hay que restar
     * a la especie. Debe ser negativo para que reste la poblacion
     *
     */
    private void reducirExceso(int depredador, float alimentoQuitar)
    {
        float pob = poblacion.get(depredador) + (alimentoQuitar / especies.get(depredador).getAlimRequerido());
        if (pob > 0) {
            poblacion.set(depredador, Math.round(pob));
        } else {
            poblacion.set(depredador, 0);
        }
    }

    /**
     * Producto de poblacion por matrizCrecimiento con la particularidad de que
     * si el resultado es menor que cero, ajusta la otra poblacion y no opera si
     * la columna o fila tiene poblacion 0 Ademas, previamente a encontrarse a
     * si mismo, calcula sobre la poblacion anterior si se ha consumido
     * demasiado, y luego al encontrarse a si mismo, elimina esa diferencia
     */
    private void producto()
    {
        float sum;
        float aux, aux2;
        int size = poblacion.size();
        ArrayList<Integer> depredados = new ArrayList();
        for (int i = 0; i < size; i++) {
            depredados.add(GetDepredados(i));
        }
        for (int i = 0; i < size; i++) {
            //Condicion para ahorrar ejecuciones
            if (poblacion.get(i) > 0) {
                //Obtencion de poblacion inicial
                sum = poblacion.get(i);
                for (int j = 0; j < size; j++) {
                    //Condicion para ahorrar ejecuciones
                    if (poblacion.get(j) > 0) {
                        if (i == j) {
                            //Resta poblacion inicial y añade la poblacion en esa iteración
                            sum -= poblacion.get(i);
                            sum += (float) (poblacion.get(i)) * matrizCrecimiento.get(i).get(j);
                            //Previene que sea menor que 0
                            if (sum < 0) {
                                sum = 0;
                            }
                        } else {
                            if (matrizCrecimiento.get(j).get(i) <= 0) {
                                //Varía la suma segun el efecto de esta especie en la otra
                                sum += (float) (poblacion.get(i)) * matrizCrecimiento.get(j).get(i) / Math.max(depredados.get(i), 1);
                                //Si reduce la especie por debajo de lo que debería, reduce los numeros de este depredador
                                if (sum < 0) {

                                    reducirExceso(j, sum / (especies.get(i).getAlimDado(true))); //Si depredados es 0 esto no es posible
                                    sum = 0;
                                }
                            } else {
                                //Comprobar que no hay sobrealimentación

                                aux2 = poblacion.get(i) * especies.get(i).getAlimDado(false); //Alim máximo dado
                                aux = poblacion.get(j) * especies.get(j).getAlimRequerido() / depredados.get(j); //Alim requerida
                                System.out.println("Simbiosis: " + especies.get(j) + " requiere: " + aux + "," + especies.get(i) + " aporta: " + aux2);
                                if (aux2 < aux) {
                                    System.out.println("Eliminando: " + (aux2 - aux / (especies.get(i).getAlimDado(false))) + " en " + especies.get(j));
                                    reducirExceso(j, aux2 - aux / (especies.get(i).getAlimDado(false)));
                                    sum += (float) (poblacion.get(i)) * matrizCrecimiento.get(j).get(i) / Math.max(depredados.get(i), 1);
                                } else {
                                    sum += (float) (poblacion.get(i)) * matrizCrecimiento.get(j).get(i) / Math.max(depredados.get(i), 1);
                                }
                            }
                        }
                    }
                    //Asigna la poblacion final a esta especie
                    poblacion.set(i, Math.round(sum));
                }
            }
        }
    }

    /**
     * Funcion para aplicar un ciclo sobre la población, luego comprueba si una
     * especie se ha quedado sin alimento, y si es asi, la elimina.
     */
    void EjecutarCiclo()
    {
        for (int i = 0; i < variacion.size(); i++) {
            variacion.set(i, poblacion.get(i));
        }
        producto();
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

        for (int i = 0; i < variacion.size(); i++) {
            variacion.set(i, poblacion.get(i) - variacion.get(i));
        }

    }

    /**
     * Devuelve la valoración de esa especie en ese ecosistema
     *
     * @param esp Especie de la que se devuelve la puntuacion
     * @return entero de puntuacion
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

    /**
     * Funcion para añadir
     */
    void anadirPoblacion(Especie esp)
    {
        poblacion.add(esp.getPoblacionInicial(bioma));
        variacion.add(0);
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
        aux += "\n" + poblacion + "\n" + variacion + "\n\n";
        for (int i = 0; i < matrizCrecimiento.size(); i++) {
            aux += matrizCrecimiento.get(i) + "\n";
        }
        return aux;
    }
}
