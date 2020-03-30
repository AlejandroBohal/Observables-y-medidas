/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cnyt.libreriacomplejos.entities;

import edu.eci.cnyt.libreriacomplejos.exceptions.LibreriaComplejosException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author sergio.bohorquez
 */
public class MatrizCompleja {
    private List<VectorComplejo> matriz;
    public static MatrizCompleja H = new MatrizCompleja(new double[][][] {{{1/Math.sqrt(2),0},{1/Math.sqrt(2),0}},
                                                                          {{1/Math.sqrt(2),0},{-1/Math.sqrt(2),0}}});
    public static MatrizCompleja X = new MatrizCompleja(new double[][][] {{{0,0},{1,0}},{{1,0},{0,0}}});
    
    
    public MatrizCompleja(int cantidadFilas, int cantidadColumnas){
        this.matriz = new ArrayList();
        for(int i=0;i<cantidadFilas;i++){
          matriz.add(new VectorComplejo(cantidadColumnas));
        }
    }
    public MatrizCompleja(double[][] vector){
        
        MatrizCompleja matrizC = new MatrizCompleja(new ArrayList());
        for (double[] vector1 : vector) {
            VectorComplejo vectorAnadir = new VectorComplejo(new ArrayList());
            for (double vector11 : vector1) {
                Complejo complejoAnadir = new Complejo(vector11, 0.0);
                vectorAnadir.add(complejoAnadir);
            }
            matrizC.add(vectorAnadir);
        }
        this.matriz = matrizC.getMatriz();
    
    }
    public MatrizCompleja(List<VectorComplejo> matriz){
        this.matriz = matriz;
    }
    public MatrizCompleja(double[][][] vector){
        
        MatrizCompleja matrizC = new MatrizCompleja(new ArrayList());
        for (double[][] vector1 : vector) {
            VectorComplejo vectorAnadir = new VectorComplejo(new ArrayList());
            for (double[] vector11 : vector1) {
                Complejo complejoAnadir = new Complejo(vector11[0], vector11[1]);
                vectorAnadir.add(complejoAnadir);
            }
            matrizC.add(vectorAnadir);
        }
        this.matriz = matrizC.getMatriz();
    
    }
    public MatrizCompleja sumar(MatrizCompleja matrizOperar) throws LibreriaComplejosException{
        for (int i=0;i<this.matriz.size();i++){
           matriz.set(i,matriz.get(i).sumar(matrizOperar.get(i)));
        }
    return this;
    }
    public MatrizCompleja restar(MatrizCompleja matrizOperar) throws LibreriaComplejosException{
        for (int i=0;i<this.matriz.size();i++){
           matriz.set(i,matriz.get(i).restar(matrizOperar.get(i)));
        }
    return this;
    }
    public MatrizCompleja inversa(){
        for (int i=0;i<this.matriz.size();i++){
           matriz.set(i,matriz.get(i).inversa());
        }
    return this;
    }
    public boolean esHermitian() throws LibreriaComplejosException{
        if(this.size()!= this.get(0).size()){
            throw new LibreriaComplejosException(LibreriaComplejosException.MATRIZ_CUADRADA);
        }
        MatrizCompleja matrizCompleja1 = new MatrizCompleja(this.getMatriz()).adjunta();
        return matrizCompleja1.equals(this);
    }
    public boolean esUnitaria() throws LibreriaComplejosException{
        if(this.size()!= this.get(0).size()){
            throw new LibreriaComplejosException(LibreriaComplejosException.MATRIZ_CUADRADA);
        }
        MatrizCompleja matrizCompleja1 = new MatrizCompleja(this.getMatriz());
        MatrizCompleja matrizCompleja2 = new MatrizCompleja(this.getMatriz()).adjunta();        
        MatrizCompleja resultado1 = new MatrizCompleja(matrizCompleja1.multiplicacion(matrizCompleja2).getMatriz());
        MatrizCompleja resultado2 = new MatrizCompleja(matrizCompleja2.multiplicacion(matrizCompleja1).getMatriz());
        return resultado1.equals(resultado2);
        
    }
    public MatrizCompleja transpuesta(){
        MatrizCompleja matrizObjetivo = new MatrizCompleja(matriz.get(0).size(),matriz.size());
        for (int i=0;i<matriz.size();i++){
            for (int j=0;j<matriz.get(i).size();j++){
                matrizObjetivo.get(j).get(i).set(matriz.get(i).get(j));
            }
        }
        this.setMatriz(matrizObjetivo.getMatriz());
        return this;
    }
    public MatrizCompleja conjugada(){
        for (int i=0;i<matriz.size();i++){
            matriz.get(i).setVector(matriz.get(i).conjugado().getVector());
        }
        return this;
    }
    public MatrizCompleja adjunta(){
        this.setMatriz(this.transpuesta().conjugada().getMatriz());
        return this;
    }
    public Complejo productoInterno(MatrizCompleja matriz) throws LibreriaComplejosException{
        
        this.matriz = this.adjunta().multiplicacion(matriz).getMatriz();
        
        return traza();
    }
    public double norma() throws LibreriaComplejosException{
        MatrizCompleja matrizCompleja = new MatrizCompleja(this.getMatriz());
        Complejo complejo = productoInterno(matrizCompleja);
        return Math.sqrt(complejo.getParteReal());
    }
    public double distancia(MatrizCompleja matrizCompleja) throws LibreriaComplejosException{
        MatrizCompleja matrizCompleja1 = new MatrizCompleja(this.getMatriz());
        MatrizCompleja matrizCompleja2 = new MatrizCompleja(matrizCompleja1.restar(matrizCompleja).getMatriz());
        return matrizCompleja2.norma();
    }
    public Complejo traza(){
        Complejo total = new Complejo();
        for (int i=0;i<this.size();i++){
            total.set(total.sumar(this.get(i).get(i)));
        }
        return total;
    }
    public MatrizCompleja multiplicacion(MatrizCompleja matriz) throws LibreriaComplejosException{
        if (this.get(0).size() != matriz.size()){
            throw new LibreriaComplejosException("El numero de columnas de la primera matriz"
                    + "debe ser igual al numero de filas de la segunda."+ "columnas "+this.get(0).size()+
                    " filas"+matriz.size());      
        }else{
            MatrizCompleja matrizObjetivo = new MatrizCompleja(this.size(),matriz.get(0).size());
            //filas matriz 1
            for (int i=0;i<this.size();i++){
                //Columnas matriz 2
                for (int j=0;j<matriz.get(0).size();j++){
                    //Columnas matriz 1
                    for (int k=0;k<this.get(0).size();k++){
                        Complejo n1 = this.get(i).get(k),n2 = matriz.get(k).get(j);
                        matrizObjetivo.get(i).get(j).
                                set(matrizObjetivo.get(i).get(j)
                                    .sumar(Complejo.multiplicar(n1, n2)));
                    }
                }
            }
            return matrizObjetivo;
        }  
    }
    public MatrizCompleja productoTensor(MatrizCompleja matriz) throws LibreriaComplejosException{
        
        int filas1= this.size(),columnas1 = this.get(0).size(),filas2 = matriz.size(),columnas2 = matriz.get(0).size();
        int filasTotal = (filas1*filas2), columnasTotal = (columnas1*columnas2);
        MatrizCompleja tensor = new MatrizCompleja(filasTotal,columnasTotal);
        
        for (int i=0; i<filasTotal; i++){
            for(int j=0; j<columnasTotal;j++){
                
                Complejo aux = this.getMatriz().get(i/filas2).get(j/columnas2);
                
                tensor.getMatriz().get(i).get(j).set(
                        this.getMatriz().get(i/filas2).get(j/columnas2).multiplicar
                        (matriz.getMatriz().get(i%filas2).get(j%columnas2)));
            }
        }
        
        return tensor;
    }
    public void add(VectorComplejo v){
        matriz.add(v);
    }
    public VectorComplejo get(int i){
        return matriz.get(i);
    }
    public double probabilidad(int posicion) throws LibreriaComplejosException{
        MatrizCompleja vectorket = new MatrizCompleja(this.size(),this.get(0).size());
        vectorket.setMatriz(this.getMatriz());
        if(this.get(0).size() > 1){
            throw new LibreriaComplejosException(LibreriaComplejosException.VECTOR_KET);
        }else{
            
            double moduloPosicionI = Math.pow(vectorket.get(posicion).get(0).getParteImaginaria(), 2);
            double moduloPosicionR = Math.pow(vectorket.get(posicion).get(0).getParteReal(), 2);
            double moduloPosicion = moduloPosicionI + moduloPosicionR;
            double sumatoriaModulos = 0.0;
            for (int i=0; i<vectorket.size();i++){
                sumatoriaModulos += Math.pow(vectorket.get(i).get(0).getParteImaginaria(),2);
                sumatoriaModulos += Math.pow(vectorket.get(i).get(0).getParteReal(),2);
            }
            return Math.pow(Math.sqrt(moduloPosicion)/Math.sqrt(sumatoriaModulos),2);
        }
    }
    public MatrizCompleja genVectorProbabilidad() throws LibreriaComplejosException{
        MatrizCompleja vector = new MatrizCompleja(this.size(),this.get(0).size());
        for (int i=0; i<this.size(); i++){
                vector.get(i).get(0).setParteReal(this.probabilidad(i));
                vector.get(i).get(0).setParteImaginaria(0.0);
        }
        return vector;
    }
    public MatrizCompleja amplitudTransicion(MatrizCompleja keth) throws LibreriaComplejosException{
        if(this.get(0).size() > 1 || keth.get(0).size() >1){
            throw new LibreriaComplejosException(LibreriaComplejosException.VECTOR_KET);
        }else{
            MatrizCompleja brah = new MatrizCompleja(keth.size(),keth.get(0).size());
            brah.setMatriz(keth.transpuesta().conjugada().getMatriz());
            MatrizCompleja amplitudDeTransicion = brah.multiplicacion(this);
            return amplitudDeTransicion;
        }
        
    }
    public double probabilidadTransicion(MatrizCompleja keth2) throws LibreriaComplejosException{
        MatrizCompleja keth1 = new MatrizCompleja(this.size(),this.get(0).size());
        keth1.setMatriz(this.getMatriz());
        MatrizCompleja resultadoM = keth1.amplitudTransicion(keth2);
        double real = resultadoM.get(0).get(0).getParteReal(),img = resultadoM.get(0).get(0).getParteImaginaria();
        double resultado = Math.pow(Math.sqrt(Math.pow(real,2)+Math.pow(img,2)),2);
        return resultado;
    }
    public Complejo media(MatrizCompleja keth) throws LibreriaComplejosException{
        if (keth.get(0).size() >1){
           throw new LibreriaComplejosException(LibreriaComplejosException.VECTOR_KET2); 
        }else{
            MatrizCompleja omega = new MatrizCompleja(this.size(),this.get(0).size());
            omega.setMatriz(this.getMatriz());
            if (! omega.esHermitian()){
                throw new LibreriaComplejosException(LibreriaComplejosException.DEBERIA_SER_HERMITIAN); 
            }
            MatrizCompleja operador1 = omega.multiplicacion(keth);
            return operador1.productoInterno(keth);
        }
    }
    public MatrizCompleja generarIdentidad(){
        MatrizCompleja In  = new MatrizCompleja(this.size(),this.get(0).size());
            for (int i=0; i<In.size(); i++){
                In.get(i).get(i).setParteReal(1.0);
                In.get(i).get(i).setParteImaginaria(0.0);
            }
        return In;
    }
    public MatrizCompleja delta(MatrizCompleja keth) throws LibreriaComplejosException{
        if (keth.get(0).size() >1){
           throw new LibreriaComplejosException(LibreriaComplejosException.VECTOR_KET2); 
        }else{
            MatrizCompleja omega = new MatrizCompleja(this.size(),this.get(0).size());
            omega.setMatriz(this.getMatriz());
            MatrizCompleja In = this.generarIdentidad();
            Complejo media = omega.media(keth);
            MatrizCompleja operador2 = In.multiplicacionEscalar(media);
            return omega.restar(operador2);
        }
    }
    public Complejo varianza(MatrizCompleja keth) throws LibreriaComplejosException{
        if (keth.get(0).size() >1){
           throw new LibreriaComplejosException(LibreriaComplejosException.VECTOR_KET2); 
        }else{
            MatrizCompleja omega = new MatrizCompleja(this.size(),this.get(0).size());
            omega.setMatriz(this.getMatriz());
            if (! omega.esHermitian()){
                throw new LibreriaComplejosException(LibreriaComplejosException.DEBERIA_SER_HERMITIAN); 
            }
            MatrizCompleja delta = omega.delta(keth);
            MatrizCompleja delta2 = delta.multiplicacion(delta);
            return delta2.media(keth);
        }
    }
    
    public List<VectorComplejo> getMatriz() {
        return matriz;
    }

    public void setMatriz(List<VectorComplejo> matriz) {
        this.matriz = matriz;
    }
    public MatrizCompleja multiplicacionEscalar(Complejo complejo){
        for(int i=0;i<matriz.size();i++){
            for(Complejo c: matriz.get(i).getVector()){
                c.multiplicar(complejo);
            }
        }
        return this;
    }
    public int size(){
        return matriz.size();
    }
    public boolean equals(MatrizCompleja matriz){
        for (int i=0;i<this.size();i++){
            if(!get(i).equals(matriz.get(i))){
                return false;
            }
        }
        return true;
    }
    @Override
    public String toString() {
        return "MatrizCompleja"+"\n"+ matriz;
    }
    public String toWolframString(){
        String respuesta ="{";
        for (int i=0; i<matriz.size();i++){
            respuesta += matriz.get(i).toWolframString();
            if (i < matriz.size()-1){
                respuesta += ",";
            }else{
                respuesta += "}";
            }
        }
        return respuesta;
           
    }

    
}
