/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cnyt.libreriacomplejos.exceptions;

/**
 *
 * @author sergio.bohorquez
 */
public class LibreriaComplejosException extends Exception {
    public static final String TAMANO_VECTOR = "Los vectores deben tener el mismo tamaño";
    public static final String TAMANO_MATRIZ = "las matrices deben ser del mismo tamaño";
    public static final String MATRIZ_CUADRADA = "la matriz debe ser cuadrada";
    public static final String VECTOR_KET = "La probabilidad solo está disponible para vectores ket";
    public static final String VECTOR_KET2 = "La media,varianza o delta de un estado necesita un keth, no otro estado.";
    public static final String DEBERIA_SER_HERMITIAN = "La media o varianza solo pueden calcularse con matrices hermitian";
    public static String getMessage(int tamañoVector1,int tamañoVector2){
        String excepcion = TAMANO_VECTOR + "Tamaño vector 1 "+ tamañoVector1 + "Tamaño vector 2 " + tamañoVector2;
        return excepcion;
    }
    public LibreriaComplejosException(String message){
       super(message);
    }
}
