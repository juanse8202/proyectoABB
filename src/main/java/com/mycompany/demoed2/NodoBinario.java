/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoed2;

/**
 *
 * @author JC
 * @param <T>
 */
public class NodoBinario<T> {
   private T dato;
   private NodoBinario<T> hijoIzquierdo;
   private NodoBinario<T> hijoDerecho;
   
   public NodoBinario() {
   }
   
   public NodoBinario(T dato){
       this.dato = dato;
   }
   
   public NodoBinario(T dato, NodoBinario<T> hijoIzquierdo,
           NodoBinario<T> hijoDerecho){
       this.dato = dato;
       this.hijoIzquierdo = hijoIzquierdo;
       this.hijoDerecho  = hijoDerecho;
   }

    public T getDato() {
        return dato;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoBinario<T> getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    public void setHijoIzquierdo(NodoBinario<T> hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    public NodoBinario<T> getHijoDerecho() {
        return hijoDerecho;
    }

    public void setHijoDerecho(NodoBinario<T> hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }
    
    public static NodoBinario nodoVacio (){
        return null;
    }
    
    public static boolean esNodoVacio(NodoBinario nodo){
        return nodo == nodoVacio();
    }
    
    public boolean esVacioHijoIzquierdo(){
        return NodoBinario.esNodoVacio(this.hijoIzquierdo);
    }
    
    public boolean esVacioHijoDerecho(){
        return NodoBinario.esNodoVacio(this.hijoDerecho);
    }

    boolean esHoja() {
        return this.esVacioHijoIzquierdo() && this.esVacioHijoDerecho();
    }
    
}
