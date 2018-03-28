package com.eci.arem;

/**
 *
 * @author Juan Camilo Mantilla
 * Clase utilizada para generar el JSON retornado al API
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
