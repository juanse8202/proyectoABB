/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.demoed2;

import com.mycompany.demoed2.excepciones.ExcepcionDatoNoExiste;
import com.mycompany.demoed2.excepciones.ExcepcionDatoYaExiste;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author JC
 * @param <T>
 */
public class ArbolBinarioBusqueda<T extends Comparable<T>> implements
        IArbolBusqueda<T> {

    protected NodoBinario<T> raiz;

    @Override
    public void insertar(T dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        if (this.esArbolVacio()) {
            this.raiz = new NodoBinario<>(dato);
            return;
        }
        NodoBinario<T> nodoActual = this.raiz;
        NodoBinario<T> nodoAnterior = NodoBinario.nodoVacio();

        do {
            T datoDelNodoAux = nodoActual.getDato();
            nodoAnterior = nodoActual;
            if (dato.compareTo(datoDelNodoAux) < 0) {
                nodoActual = nodoActual.getHijoIzquierdo();
            } else if (dato.compareTo(datoDelNodoAux) > 0) {
                nodoActual = nodoActual.getHijoDerecho();
            } else {
                throw new ExcepcionDatoYaExiste();
            }
        } while (!NodoBinario.esNodoVacio(nodoActual));

        /*
        while (!NodoBinario.esNodoVacio(nodoActual)){
            T datoActual = nodoActual.getDato();
            nodoAnterior = nodoActual;
            if(datoAInsertar.compareTo(datoActual)<0){
                nodoActual = nodoActual.getHijoIzquierdo();
            }else if(datoAInsertar.compareTo(datoActual)>0){
                nodoActual = nodoActual.getHijoDerecho();
            }else{
                nodoActual.setDato(datoAInsertar);
                return;
            }
        }
         */
        // Si llego hasta este punto, quiere decir que no existe en el arbol
        // la clave, entonces debo crear un nodo, con el dato a insertar
        // y el nodoAnterior es el padre de ese nuevo nodo
        NodoBinario<T> nuevoNodo = new NodoBinario<>(dato);
        //  T datoDelPadre = nodoAnterior.getDato();
        if (dato.compareTo(nodoAnterior.getDato()) < 0) {
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
        } else {
            nodoAnterior.setHijoDerecho(nuevoNodo);
        }
    }

    @Override
    public void eliminar(T datoAEliminar) {
        this.raiz = eliminar(this.raiz, datoAEliminar);
    }

    private NodoBinario<T> eliminar(NodoBinario<T> nodoActual, T datoAEliminar) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            throw new ExcepcionDatoNoExiste();
        }
        T datoActual = nodoActual.getDato();
        if (datoAEliminar.compareTo(datoActual) < 0) {
            NodoBinario<T> supuestoNuevoHI = eliminar(nodoActual.getHijoIzquierdo(), datoAEliminar);
            nodoActual.setHijoIzquierdo(supuestoNuevoHI);
            return nodoActual;
        }
        if (datoAEliminar.compareTo(datoActual) > 0) {
            NodoBinario<T> supuestoNuevoHD = eliminar(nodoActual.getHijoDerecho(), datoAEliminar);
            nodoActual.setHijoDerecho(supuestoNuevoHD);
            return nodoActual;
        }
        //caso 1
        if (nodoActual.esHoja()) {
            return NodoBinario.nodoVacio();
        }
        //caso 2
        if (!nodoActual.esVacioHijoIzquierdo() && nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoIzquierdo();
        }
        if (nodoActual.esVacioHijoIzquierdo() && !nodoActual.esVacioHijoDerecho()) {
            return nodoActual.getHijoDerecho();
        }
        //caso 3
        T reemplazo = (T) obtenerSucesorInOrden(nodoActual.getHijoDerecho());
        NodoBinario<T> supuestoNuevoHD = eliminar(nodoActual.getHijoDerecho(), reemplazo);
        nodoActual.setHijoDerecho(supuestoNuevoHD);
        nodoActual.setDato(reemplazo);
        return nodoActual;
    }

    public NodoBinario<T> obtenerSucesorInOrden(NodoBinario<T> hijoDerecho) {
        NodoBinario<T> nodoAnterior;
        do {
            nodoAnterior = hijoDerecho;
            hijoDerecho = hijoDerecho.getHijoIzquierdo();
        } while (!NodoBinario.esNodoVacio(hijoDerecho));
        return nodoAnterior;
    }

    @Override
    public boolean contiene(T dato) {
        return this.buscar(dato) != null;
    }
    //si o si se necesita un nodo binario

    @Override
    public int size() {
        int cantidadDeNodos = 0;
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<T>> pilaDeNodos = new Stack();
            pilaDeNodos.push(this.raiz);
            do {
                NodoBinario<T> nodoAux = pilaDeNodos.pop();
                cantidadDeNodos++;
                if (!nodoAux.esVacioHijoDerecho()) {
                    pilaDeNodos.push(nodoAux.getHijoDerecho());
                }
                if (!nodoAux.esVacioHijoIzquierdo()) {
                    pilaDeNodos.push(nodoAux.getHijoIzquierdo());
                }
            } while (!pilaDeNodos.isEmpty());
        }
        return cantidadDeNodos;
    }

    @Override
    public int sizeR() {
        return sizeR(this.raiz);
    }

    private int sizeR(NodoBinario<T> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int sizePorIzquierda = sizeR(nodoActual.getHijoIzquierdo());
        int sizePorDerecha = sizeR(nodoActual.getHijoDerecho());
        return sizePorIzquierda + sizePorDerecha + 1;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }

    protected int altura(NodoBinario<T> nodoActual) {
        if (NodoBinario.esNodoVacio(nodoActual)) {
            return 0;
        }
        int alturaPorIzq = altura(nodoActual.getHijoIzquierdo());
        int alturaPorDer = altura(nodoActual.getHijoDerecho());
        return alturaPorIzq > alturaPorDer ? alturaPorIzq + 1 : alturaPorDer + 1;
    }

    @Override

    public void vaciar() {
        this.raiz = (NodoBinario<T>) NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        return NodoBinario.esNodoVacio(this.raiz);
    }

    @Override
    public int nivel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<T> recorridoEnInOrden() { // pregunta de examen
        List<T> recorrido = new ArrayList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            this.meterAlaPilaParaInOrden(pilaDeNodos, this.raiz);
            do {
                NodoBinario<T> nodoActual = pilaDeNodos.pop();
                recorrido.add(nodoActual.getDato());
                if (!nodoActual.esVacioHijoDerecho()) {
                    this.meterAlaPilaParaInOrden(pilaDeNodos, nodoActual.getHijoDerecho());
                }
            } while (!pilaDeNodos.isEmpty());
        }
        return recorrido;
    }
    
    private void meterAlaPilaParaInOrden(Stack<NodoBinario<T>> pilaDeNodos,NodoBinario nodoActual){
        while(!NodoBinario.esNodoVacio(nodoActual)){
            pilaDeNodos.push(nodoActual);
            nodoActual=nodoActual.getHijoIzquierdo();
        }
    }

    @Override
    public List<T> recorridoPreOrden() {
        List<T> listaDeDatos = new LinkedList<>();
        if (!this.esArbolVacio()) {
            Stack<NodoBinario<T>> pilaDeNodos = new Stack<>();
            pilaDeNodos.push(this.raiz);

            do {
                NodoBinario<T> nodoQueTocaSacar = pilaDeNodos.pop();
                listaDeDatos.add(nodoQueTocaSacar.getDato());
                if (!nodoQueTocaSacar.esVacioHijoDerecho()) {
                    pilaDeNodos.push(nodoQueTocaSacar.getHijoDerecho());
                }
                if (!nodoQueTocaSacar.esVacioHijoIzquierdo()) {
                    pilaDeNodos.push(nodoQueTocaSacar.getHijoIzquierdo());
                }
            } while (!pilaDeNodos.isEmpty());
        }
        return listaDeDatos;
    }

    @Override
    public List<T> recorridoPostOrden() {
       List<T> listaDeDatos=new LinkedList<>();
       if (! this.esArbolVacio()){
          Stack<NodoBinario<T>> pilaDeNodos=new Stack<>();
          NodoBinario<T> nodoAux=this.raiz;
          meterEnPilaParaPostOrden(nodoAux, pilaDeNodos);
          do{
              nodoAux=pilaDeNodos.pop();
              listaDeDatos.add(nodoAux.getDato());
              
              if (!pilaDeNodos.empty()){
                 NodoBinario<T> nodoTope=pilaDeNodos.peek();
                 if (!nodoTope.esVacioHijoDerecho() && nodoAux !=nodoTope.getHijoDerecho()){
                    nodoAux=nodoTope.getHijoDerecho();
                    meterEnPilaParaPostOrden(nodoAux, pilaDeNodos);
                 }
              }
          }while (!pilaDeNodos.isEmpty());
       }
       return listaDeDatos;      
    }
    
    private void meterEnPilaParaPostOrden(NodoBinario<T> nodoAux, Stack<NodoBinario<T>> pilaDeNodos){
       while(!NodoBinario.esNodoVacio(nodoAux)){
            pilaDeNodos.push(nodoAux);
            if (!nodoAux.esVacioHijoIzquierdo()){
               nodoAux=nodoAux.getHijoIzquierdo();
            }else{
               nodoAux=nodoAux.getHijoDerecho();
            }
       }
    }

    @Override
    public List<T> recorridoPorNiveles() {
        List<T> listaDeNodos = new LinkedList<>();
        if (!this.esArbolVacio()) {
            Queue<NodoBinario<T>> colaDeNodos = new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            do {
                NodoBinario<T> nodoQueTocaSacar = colaDeNodos.poll();
                listaDeNodos.add(nodoQueTocaSacar.getDato());
                if (!nodoQueTocaSacar.esVacioHijoIzquierdo()) {
                    colaDeNodos.offer(nodoQueTocaSacar.getHijoIzquierdo());
                }
                if (!nodoQueTocaSacar.esVacioHijoDerecho()) {
                    colaDeNodos.offer(nodoQueTocaSacar.getHijoDerecho());
                }
            } while (!colaDeNodos.isEmpty());
        }
        return listaDeNodos;
    }

    @Override
    public T buscar(T dato) {
        NodoBinario<T> nodoAux = this.raiz;
        while (!NodoBinario.esNodoVacio(nodoAux)) {
            T datoDelNodoAux = nodoAux.getDato();
            if (dato.compareTo(datoDelNodoAux) < 0) {
                nodoAux = nodoAux.getHijoIzquierdo();
            } else if (dato.compareTo(datoDelNodoAux) > 0) {
                nodoAux = nodoAux.getHijoDerecho();
            } else {
                return datoDelNodoAux;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
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

    protected boolean estaVacio(NodoBinario<T> nodoActual) {
        return nodoActual == null;
    }

    
}
