/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cnyt.libreriacomplejos.entities;

/**
 *
 * @author 2146516
 */
public class ComplejoPolar {
    private double r;
    private double thetha;
    public ComplejoPolar(double r,double thetha){
        this.r = r;
        this.thetha = thetha;
    }
    public ComplejoPolar(){
        this.r = 0.0;
        this.thetha = 0.0;
    }
    

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getThetha() {
        return thetha;
    }

    public void setThetha(double thetha) {
        this.thetha = thetha;
    }
     public boolean equals(ComplejoPolar complejo){
        if (Math.abs(this.r - complejo.r) <= 0.00001 
            & Math.abs(this.thetha - complejo.thetha) <= 0.00001){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "ComplejoPolar{" + "r=" + r + ", thetha=" + thetha + '}';
    }
    
}
