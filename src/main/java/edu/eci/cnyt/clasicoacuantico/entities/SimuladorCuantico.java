/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cnyt.clasicoacuantico.entities;

import edu.eci.cnyt.libreriacomplejos.entities.MatrizCompleja;
import edu.eci.cnyt.libreriacomplejos.exceptions.LibreriaComplejosException;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.BarRenderer;

/**
 * Programming drill 3.1.1
 * @author AlejandroB
 */
public class SimuladorCuantico {
    private MatrizCompleja estadoInicial;
    private MatrizCompleja matrizEstados;
    private MatrizCompleja estadoActual;
    private MatrizCompleja vectorProbabilidad;
    
    /**
     * Constructor con valores booleanos, reales o complejos.
     * @param estadoInicial valor del estado inicial
     * @param matrizEstados matriz de estados booleanos
     * 
     */
    public SimuladorCuantico(MatrizCompleja estadoInicial, MatrizCompleja matrizEstados) {
        
        this.estadoInicial = estadoInicial;
        this.matrizEstados = matrizEstados;
        this.estadoActual = estadoInicial;
    }
    /**
     * Método que hace cambios en el estado del sistema dado estado inicial y matriz estados
     * @param numeroClicks cantidad de ves que se hará click
     * @return 
     */
    public MatrizCompleja click(int numeroClicks) throws LibreriaComplejosException{
        MatrizCompleja auxEstadoFinal  = new MatrizCompleja(estadoInicial.size(),estadoInicial.get(0).size());
        auxEstadoFinal.setMatriz(estadoInicial.getMatriz());
        MatrizCompleja auxMatrizEstados  = new MatrizCompleja(estadoInicial.size(),estadoInicial.get(0).size());
        auxMatrizEstados.setMatriz(matrizEstados.getMatriz());
        for (int i=0;i<numeroClicks;i++){
            auxEstadoFinal.setMatriz(auxMatrizEstados.multiplicacion(auxEstadoFinal).getMatriz());
            
        }
        
        estadoActual.setMatriz(auxEstadoFinal.getMatriz());
        return auxEstadoFinal;
    }
    public void volverAEstadoInicial(){
        estadoActual.setMatriz(estadoInicial.getMatriz());
    }
    
    
    public MatrizCompleja genVectorProbabilidad() throws LibreriaComplejosException{
        MatrizCompleja vector = new MatrizCompleja(this.getEstadoActual().size(),this.getEstadoActual().get(0).size());
        for (int i=0; i<estadoActual.size(); i++){
                vector.get(i).get(0).setParteReal(estadoActual.probabilidad(i));
                vector.get(i).get(0).setParteImaginaria(0.0);
        }
        return vector;
    }
   
    public MatrizCompleja getEstadoInicial() {
        return estadoInicial;
    }
    

    public void setEstadoInicial(MatrizCompleja estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    public MatrizCompleja getMatrizEstados() {
        return matrizEstados;
    }

    public void setMatrizEstados(MatrizCompleja matrizEstados) {
        this.matrizEstados = matrizEstados;
    }

    public MatrizCompleja getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(MatrizCompleja estadoActual) {
        this.estadoActual = estadoActual;
    }
    
    public void generarBarras(String titulo) throws IOException, LibreriaComplejosException{
       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       MatrizCompleja probabilidad = this.genVectorProbabilidad();
       for (int i=0; i<probabilidad.size(); i++){
           double y = probabilidad.get(i).get(0).getParteReal();
           String x = "x("+i+")";
           dataset.addValue(y, x, x);
       }
       JFreeChart chart = ChartFactory.createBarChart(titulo,"Posición", "Probabilidad", dataset, PlotOrientation.VERTICAL, true, true, true);
       ChartPanel panel = new ChartPanel(chart);
       BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
       renderer.setItemMargin(-2);
       Plot plot = chart.getPlot();
       plot.setBackgroundPaint(new Color(255,255,255));
       
       File dir = new File("GraficasSimulaciones");
       dir.mkdirs();
       File tmp = new File(dir, titulo+".jpg");       
       ChartUtilities.saveChartAsJPEG(tmp, chart, 800, 500);
       
       
    }

    public MatrizCompleja getVectorProbabilidad() {
        return vectorProbabilidad;
    }

    public void setVectorProbabilidad(MatrizCompleja vectorProbabilidad) {
        this.vectorProbabilidad = vectorProbabilidad;
    }
    
}
