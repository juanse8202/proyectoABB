/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoed2.excepciones;

/**
 *
 * @author JC
 */
public class NewException extends Exception{

    public NewException() {
        super("Dato no existe en el arbol");
    }

    public NewException(String message) {
        super(message);
    }
    
}
