/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eci.arem;

/**
 *
 * @author Juan Camilo Mantilla
 */
public class Response {
    private int valor;
    private int cuadrado;
    
    public Response(int valor, int cuadrado){
        this.valor = valor;
        this.cuadrado = cuadrado;
    }
    
    public int getValor(){
        return valor;
    }
    
    public int getCuadrado(){
        return cuadrado;
    }
    
    public void setValor(int valor){
        this.valor = valor;
    }
    
    public void setCuadrado(int cuadrado){
        this.cuadrado = cuadrado;
    }
}