/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.demoed2;

/**
 *
 * @author JC
 */
public class DemoEd2 {

    public static void main(String[] args) {
    /*    IArbolBusqueda<Integer> arbolBinario = new ArbolBinarioBusqueda<>();
        arbolBinario.insertar(100);
        arbolBinario.insertar(200);
        arbolBinario.insertar(70);
        arbolBinario.insertar(60); 
        arbolBinario.insertar(55);
        arbolBinario.insertar(80);
        System.out.println(arbolBinario);
        System.out.println("Recorrido por niveles: "+ arbolBinario.recorridoPorNiveles());
        System.out.println("Buscar : "+ arbolBinario.buscar(56));
        System.out.println("Recorrido en InOrden: "+ arbolBinario.recorridoEnInOrden());
        System.out.println("Recorrido en preorden: "+ arbolBinario.recorridoPreOrden());
        arbolBinario.eliminar(80);
        System.out.println(arbolBinario);
    */    
        
        System.out.println("--------------------------------");
        IArbolBusqueda<Integer> arbolAVL = new AVL<>();
        arbolAVL.insertar(100);
        arbolAVL.insertar(200);
        arbolAVL.insertar(70);
        arbolAVL.insertar(60); 
        arbolAVL.insertar(55);
        System.out.println(arbolAVL.toString());
        arbolAVL.eliminar(100);
        System.out.println(arbolAVL.toString());
        System.out.println(" "+ arbolAVL.recorridoPorNiveles());
    }
}
