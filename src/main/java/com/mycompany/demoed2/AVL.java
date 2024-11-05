/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoed2;

import com.mycompany.demoed2.excepciones.ExcepcionDatoYaExiste;

/**
 *
 * @author JC
 * @param <T>
 */
public class AVL<T extends Comparable<T>> extends ArbolBinarioBusqueda<T> {

    private static final int RANGO_INFERIOR = -1;
    private static final int RANGO_SUPERIOR = 1;

    @Override
    public void insertar(T datoAInsertar) {
        this.raiz = insertar(this.raiz, datoAInsertar);
    }

    private NodoBinario<T> insertar(NodoBinario<T> nodoActual, T datoAInsertar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return new NodoBinario<>(datoAInsertar);
        }
        T datoActual = nodoActual.getDato();
        if (datoAInsertar.compareTo(datoActual) < 0) {
            NodoBinario<T> supuestoNuevoHI = insertar(nodoActual.getHijoIzquierdo(), datoAInsertar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHI);
            return balancear(nodoActual);
        }
        if (datoAInsertar.compareTo(datoActual) > 0) {
            NodoBinario<T> supuestoNuevoHD = insertar(nodoActual.getHijoDerecho(), datoAInsertar);
            nodoActual.setHijoDerecho(supuestoNuevoHD);
            return balancear(nodoActual);
        }
        throw new ExcepcionDatoYaExiste();
    }

    private NodoBinario<T> balancear(NodoBinario<T> nodo) {
        int alturaPorIzq = super.altura(nodo.getHijoIzquierdo());
        int alturaPorDer = super.altura(nodo.getHijoDerecho());
        int diferenciaDeAltura = alturaPorIzq - alturaPorDer;
        if (diferenciaDeAltura > RANGO_SUPERIOR) {
            NodoBinario<T> nodoAux = nodo.getHijoIzquierdo();
            alturaPorIzq = super.altura(nodoAux.getHijoIzquierdo());
            alturaPorDer = super.altura(nodoAux.getHijoDerecho());
            if (alturaPorDer > alturaPorIzq) {
                return rotacionDoblePorDerecha(nodo);
            }
            return rotacionSimplePorDerecha(nodo);
        }
        if (diferenciaDeAltura < RANGO_INFERIOR) {
            NodoBinario<T> nodoAux = nodo.getHijoDerecho();
            alturaPorIzq = super.altura(nodoAux.getHijoIzquierdo());
            alturaPorDer = super.altura(nodoAux.getHijoDerecho());
            if (alturaPorDer < alturaPorIzq) {
                return rotacionDoblePorIzquierda(nodo);
            }
            return rotacionSimplePorIzquierda(nodo);
        }
        return nodo;
    }

    private NodoBinario<T> rotacionSimplePorDerecha(NodoBinario<T> nodoActual) {
        NodoBinario<T> nodoQueRota = nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<T> rotacionDoblePorDerecha(NodoBinario<T> nodoActual) {
        NodoBinario<T> nodoQueRotaAIzquierda = rotacionSimplePorIzquierda(nodoActual.getHijoIzquierdo());
        nodoActual.setHijoIzquierdo(nodoQueRotaAIzquierda);
        return rotacionSimplePorDerecha(nodoActual);
    }

    private NodoBinario<T> rotacionSimplePorIzquierda(NodoBinario<T> nodoActual) {
        NodoBinario<T> nodoQueRota = nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;
    }

    private NodoBinario<T> rotacionDoblePorIzquierda(NodoBinario<T> nodoActual) {
        NodoBinario<T> nodoQueRotaADerecha = rotacionSimplePorDerecha(
                nodoActual.getHijoDerecho());
        nodoActual.setHijoDerecho(nodoQueRotaADerecha);
        return rotacionSimplePorIzquierda(nodoActual);
    }

    @Override
    public void eliminar(T datoAEliminar){
        if (datoAEliminar == null) {
            throw new IllegalArgumentException("Clave invalida,no se aceptan claves nulas");
        }

        this.raiz = eliminar(this.raiz, datoAEliminar);

    }

    private NodoBinario<T> eliminar(NodoBinario<T> nodoActual,
            T datoAEliminar) {
        T datoActual = nodoActual.getDato();
        if (datoAEliminar.compareTo(datoActual) < 0) {
            NodoBinario<T> supuestoNuevoHijoIzquierdo
                    = eliminar(nodoActual.getHijoIzquierdo(), datoAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHijoIzquierdo);
            return balancear(nodoActual);
        }
        if (datoAEliminar.compareTo(datoActual) > 0) {
            NodoBinario<T> supuestoNuevoHijoDerecho
                    = eliminar(nodoActual.getHijoDerecho(), datoAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHijoDerecho);
            return balancear(nodoActual);
        }

        // CASO 1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }

        // CASO2
        if (nodoActual.esVacioHijoIzquierdo()
                && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDerecho();
        }
        if (!nodoActual.esVacioHijoIzquierdo()
                && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }

        // CASO 3
        NodoBinario<T> nodoDelSucesor = (NodoBinario<T>) super.obtenerSucesorInOrden(nodoActual.getHijoDerecho());
        NodoBinario<T> posibleNuevoHijoDerecho
                = eliminar(nodoActual.getHijoDerecho(), nodoDelSucesor.getDato());

        nodoActual.setHijoDerecho(posibleNuevoHijoDerecho);
        nodoActual.setDato(nodoDelSucesor.getDato());

        return nodoActual;
    }
    
    @Override
    public String toString() {
        return ToStrinNodo(raiz, "");
    }

    private String ToStrinNodo(NodoBinario<T> nodoActual, String ant) {
        if (estaVacio(nodoActual)) {
            return "";
        }
        String g;
        g = "{" + nodoActual.getDato().toString() + "}\n";
        g += ant + "|\n";
        g += ant + "|=Der-> " + ToStrinNodo(nodoActual.getHijoDerecho(), ant + "|  ") + "\n";
        g += ant + "|\n";
        g += ant + "|=Izq-> " + ToStrinNodo(nodoActual.getHijoIzquierdo(), ant + "    ");
        return g;
    }
}
