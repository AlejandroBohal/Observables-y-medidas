/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cnyt.libreriacomplejos;

import edu.eci.cnyt.clasicoacuantico.entities.SimuladorCuantico;
import edu.eci.cnyt.libreriacomplejos.entities.MatrizCompleja;
import edu.eci.cnyt.libreriacomplejos.exceptions.LibreriaComplejosException;
import edu.eci.cnyt.wolfram.WolframQuery;
import java.util.List;
import org.junit.Test;

/**
 *
 * @author AlejandroB
 */
public class observablesYMedidasTest {

    @Test
    public void testPunto431() {
        System.out.println("Punto 4.3.1 \n\n");
        MatrizCompleja ObservableSz = new MatrizCompleja(new double[][][]{
            {{1.0, 0.0}, {0.0, 0.0}},
            {{0.0, 0.0}, {-1.0, 0.0}}
        });
        MatrizCompleja ObservableSx = new MatrizCompleja(new double[][][]{
            {{0.0, 0.0}, {1.0, 0.0}},
            {{1.0, 0.0}, {0.0, 0.0}}
        });
        MatrizCompleja ObservableSy = new MatrizCompleja(new double[][][]{
            {{0.0, 0.0}, {0.0, -1.0}},
            {{0.0, 1.0}, {0.0, 0.0}}
        });
        WolframQuery query = new WolframQuery();
        List<String> posiblesEstadosTransicionSz = query.vectoresPropios(ObservableSz.toWolframString());
        List<String> posiblesEstadosTransicionSy = query.vectoresPropios(ObservableSy.toWolframString());
        List<String> posiblesEstadosTransicionSx = query.vectoresPropios(ObservableSx.toWolframString());
        System.out.println("Estados a los que puede ir Sz "+ "h/2*"+posiblesEstadosTransicionSz);
        System.out.println("Estados a los que puede ir Sy "+ "h/2*"+posiblesEstadosTransicionSy);
        System.out.println("Estados a los que puede ir Sx "+ "h/2*"+posiblesEstadosTransicionSx);
        System.out.println("\n\n");
    }
    @Test
    public void testPunto432() throws LibreriaComplejosException{
        System.out.println("Punto 4.3.2 \n\n");
        MatrizCompleja estadoInicialSy = new MatrizCompleja(new double[][][]{
            {{0.0,0.0}},
            {{0.0,-1.0}}
        });
        MatrizCompleja estadoAlQuePuedeTransitar1 = new MatrizCompleja(new double[][][]{
            {{0.0,1.0}},
            {{1.0,0.0}}
        });
        MatrizCompleja estadoAlQuePuedeTransitar2 = new MatrizCompleja(new double[][][]{
            {{0.0,-1.0}},
            {{1.0,0.0}}
        });
        System.out.println("Probabilidad transicion" +estadoInicialSy.probabilidadTransicion(estadoAlQuePuedeTransitar1));
        System.out.println("Probabilidad transicion" +estadoInicialSy.probabilidadTransicion(estadoAlQuePuedeTransitar2));
        System.out.println("\n\n");
    }
    @Test
    public void testPunto441() throws LibreriaComplejosException{
        System.out.println("Punto 4.4.1 \n\n");
        MatrizCompleja matrizU1 = new MatrizCompleja(new double[][][]{
            {{0.0,0.0},{1.0,0.0}},
            {{1.0,0.0},{0.0,0.0}} 
        });
        MatrizCompleja matrizU2 = new MatrizCompleja(new double[][]{
            {(1.0/Math.sqrt(2)),(1.0/Math.sqrt(2))},
            {(1.0/Math.sqrt(2)),(-1.0/Math.sqrt(2))} 
        });
        MatrizCompleja matrizMultiplicacion = matrizU1.multiplicacion(matrizU2);
        System.out.println("MatrizU1 es unitaria: "+ matrizU1.esUnitaria());
        System.out.println("MatrizU2 es unitaria: "+ matrizU2.esUnitaria());
        System.out.println("MatrizMultiplicacion es unitaria: "+ matrizMultiplicacion.esUnitaria());
        System.out.println("\n\n");
    }
    @Test
    public void testPunto442() throws LibreriaComplejosException{
        System.out.println("Punto 4.4.1 \n\n");
        MatrizCompleja estadoInicial = new MatrizCompleja(new double[][]{
            {1.0},
            {0.0},
            {0.0},
            {0.0},
        });
        MatrizCompleja matrizEstados = new MatrizCompleja(new double[][][]{
            {{0.0,0.0},{(1.0/Math.sqrt(2)),0.0},{(1.0/Math.sqrt(2)),0.0},{0.0,0.0}},
            {{0.0,(1.0/Math.sqrt(2))},{0.0,0.0},{0.0,0.0},{(1.0/Math.sqrt(2)),0.0}},
            {{(1.0/Math.sqrt(2)),0.0},{0.0,0.0},{0.0,0.0},{0.0,(1.0/Math.sqrt(2))}},
            {{0.0,0.0},{(1.0/Math.sqrt(2)),0.0},{(-1.0/Math.sqrt(2)),0.0},{0.0,0.0}},
        });
        SimuladorCuantico simulador = new SimuladorCuantico(estadoInicial,matrizEstados);
        simulador.click(3);
        System.out.print("Estado Actual \n"+simulador.getEstadoActual());
        System.out.print("Probabilidad de ir a 3 \n"+simulador.genVectorProbabilidad().get(3));
        System.out.println("\n\n");
    }
}
