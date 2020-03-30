/*
 * Libreria de complejos realizada en clase de Ciencias Naturales y tecnologia
 * Introduccion a computacion cuantica
 * and open the template in the editor.
 */
package edu.eci.cnyt.libreriacomplejos.entities;
import java.lang.Math;
/**
 *
 * @author Sergio Alejandro Bohorquez Alzate
 */
public class Complejo {
    private double parteReal;
    private double parteImaginaria;
    
    
    public Complejo(){
        this.parteReal = 0.0;
        this.parteImaginaria = 0.0;
    }
    public Complejo(double parteReal,double parteImaginaria){
        this.parteReal = parteReal;
        this.parteImaginaria = parteImaginaria;
        
    }
   
    public Complejo sumar(Complejo complejo){
        this.parteReal += complejo.parteReal;
        this.parteImaginaria += complejo.parteImaginaria;
        return this;
    }
    public Complejo restar(Complejo complejo){
        this.parteReal -= complejo.parteReal;
        this.parteImaginaria -= complejo.parteImaginaria;
        return this;
    }
    public Complejo multiplicar(Complejo complejo){
        double parteRealO = this.parteReal;
        double parteImaginariaO = this.parteImaginaria;
        this.parteReal = parteRealO*complejo.parteReal - parteImaginariaO*complejo.parteImaginaria;
        this.parteImaginaria = parteRealO*complejo.parteImaginaria+parteImaginariaO*complejo.parteReal;
        return this;
    }
    public static Complejo multiplicar(Complejo complejo1,Complejo complejo2){
        Complejo resultado = new Complejo();
        resultado.parteReal = complejo1.parteReal*complejo2.parteReal - complejo1.parteImaginaria*complejo2.parteImaginaria;
        resultado.parteImaginaria = complejo1.parteReal*complejo2.parteImaginaria+complejo1.parteImaginaria*complejo2.parteReal;
        return resultado;
    }
    public Complejo dividir(Complejo complejo){
        double parteRealO = this.parteReal;
        double parteImaginariaO = this.parteImaginaria;
        this.parteReal = (parteRealO*complejo.parteReal+parteImaginariaO*complejo.parteImaginaria)/
                          ((complejo.parteReal*complejo.parteReal) + (complejo.parteImaginaria*complejo.parteImaginaria));
        this.parteImaginaria = (complejo.parteReal*parteImaginariaO - parteRealO*complejo.parteImaginaria)/
                          ((complejo.parteReal*complejo.parteReal) + (complejo.parteImaginaria*complejo.parteImaginaria));
        return this;
    }
    public double modulo(){
        return Math.sqrt( this.parteReal*this.parteReal + this.parteImaginaria*this.parteImaginaria);
    }
    public Complejo conjugado(){
        this.parteImaginaria = -1.0*this.parteImaginaria;
        return this;
    }
    public Complejo negativo(){
        this.parteImaginaria = -1.0*this.parteImaginaria;
        this.parteReal = -1.0*this.parteReal;
        return this;
    }
    
    public ComplejoPolar cartesianaAPolar(){
        ComplejoPolar respuesta = new ComplejoPolar(0,0);
        respuesta.setR(Math.sqrt( this.parteReal*this.parteReal + this.parteImaginaria*this.parteImaginaria));
        respuesta.setThetha(Math.atan(this.parteImaginaria/this.parteReal));
        return respuesta;
    }

    public double getParteReal() {
        return parteReal;
    }

    public void setParteReal(double parteReal) {
        this.parteReal = parteReal;
    }

    public double getParteImaginaria() {
        return parteImaginaria;
    }

    public void setParteImaginaria(double parteImaginaria) {
        this.parteImaginaria = parteImaginaria;
    }
    public void set(Complejo c){
        this.parteImaginaria = c.parteImaginaria;
        this.parteReal = c.parteReal;
    }
    @Override
    public String toString() {
        if (parteImaginaria != 0.0){
            return "(" + parteReal + "," + parteImaginaria + ')';
        }else{
            return String.valueOf(parteReal);
        }
        
    }
    public String toWolframString(){

        if (parteImaginaria == 0){
            return parteReal +"";
        }
        else if(parteImaginaria<0 && parteImaginaria!=0){
            return parteReal +""+ parteImaginaria+"i";
        }else{
            return parteReal +"+"+ parteImaginaria+"i";
        }
    }
    
    public boolean equals(Complejo complejo){
        if (Math.abs(this.parteReal - complejo.parteReal) <= 0.00001 
            & Math.abs(this.parteImaginaria - complejo.parteImaginaria) <= 0.00001){
            return true;
        }else{
            return false;
        }
    }
    public double fase(){
        return Math.atan2(parteImaginaria, parteReal);
    }
    
    
    
}
