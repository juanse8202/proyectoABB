/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.demoed2;

import java.util.List;

/**
 *
 * @author JC
 * @param <T>
 */
public interface IArbolBusqueda<T extends Comparable<T>> {
    void insertar(T dato);
    void eliminar(T datoAEliminar);
    T buscar(T dato);
    boolean contiene(T dato);
    int size();
    int sizeR();
    int altura();
    void vaciar();
    boolean esArbolVacio();
    int nivel();
    List<T> recorridoEnInOrden();
    List<T> recorridoPreOrden();
    List<T> recorridoPostOrden();
    List<T> recorridoPorNiveles();
    
}
