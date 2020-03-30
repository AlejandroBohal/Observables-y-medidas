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
public class VectorComplejo {

    private List<Complejo> vector;

    public VectorComplejo(int cantidadNumeros) {
        vector = new ArrayList();
        for (int i = 0; i < cantidadNumeros; i++) {
            add(new Complejo());
        }
    }
    public VectorComplejo(double[][] vector){
        VectorComplejo vectorAnadir = new VectorComplejo(new ArrayList());
            for (double[] vector11 : vector) {
                Complejo complejoAnadir = new Complejo(vector11[0], vector11[1]);
                vectorAnadir.add(complejoAnadir);
            }
        this.vector = vectorAnadir.getVector();
    }

    public VectorComplejo(List<Complejo> vector) {
        this.vector = vector;
    }

    public VectorComplejo sumar(VectorComplejo vectorComplejo) throws LibreriaComplejosException {
        if (vectorComplejo.size() != this.size()) {
            throw new LibreriaComplejosException(LibreriaComplejosException.getMessage(this.size(), vectorComplejo.size()));
        }
        for (int i = 0; i < vector.size(); i++) {
            vector.set(i, vector.get(i).sumar(vectorComplejo.get(i)));
        }
        return this;
    }

    public VectorComplejo restar(VectorComplejo vectorComplejo) throws LibreriaComplejosException {
        if (vectorComplejo.size() != this.size()) {
            throw new LibreriaComplejosException(LibreriaComplejosException.getMessage(this.size(), vectorComplejo.size()));
        }
        for (int i = 0; i < vector.size(); i++) {
            vector.set(i, vector.get(i).restar(vectorComplejo.get(i)));
        }
        return this;
    }

    public VectorComplejo conjugado() {
        for (int i = 0; i < vector.size(); i++) {
            vector.set(i, vector.get(i).conjugado());
        }
        return this;
    }

    public VectorComplejo inversa() {
        for (int i = 0; i < vector.size(); i++) {
            vector.set(i, vector.get(i).negativo());
        }
        return this;
    }

    public VectorComplejo multiplicacionEscalar(Complejo complejo) {
        for (int i = 0; i < vector.size(); i++) {
            vector.set(i, vector.get(i).multiplicar(complejo));
        }
        return this;
    }

    public Complejo get(int i) {
        return vector.get(i);
    }

    public List<Complejo> getVector() {
        return vector;
    }

    public void setVector(List<Complejo> vector) {
        this.vector = vector;
    }
    
    public void add(Complejo c) {
        this.vector.add(c);
    }

    public int size() {
        return vector.size();
    }

    @Override
    public String toString() {
        return vector + "\n";
    }
    public String toWolframString(){
        String respuesta ="{";
        for (int i=0; i<vector.size();i++){
            respuesta += vector.get(i).toWolframString();
            if (i < vector.size()-1){
                respuesta += ",";
            }else{
                respuesta += "}";
            }
        }
        return respuesta;
           
    }
    public boolean equals(VectorComplejo vector){
        for (int i=0;i<this.size();i++){
            if(!get(i).equals(vector.get(i))){
                return false;
            }
        }
        return true;
    }
    
}
