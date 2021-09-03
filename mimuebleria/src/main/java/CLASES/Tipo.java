/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

/**
 *
 * @author Alex
 */
public class Tipo {

    private int idTipoPieza, cantidad;
    private String nombre;

    public Tipo(int idTipoPieza, String nombre) {
        this.idTipoPieza = idTipoPieza;
        this.nombre = nombre;
    }

    public int getIdTipoPieza() {
        return idTipoPieza;
    }

    public void agregarUnidades(int cantidad) {
        this.cantidad += cantidad;
    }

    public void restarUnidades(int cantidad) {
        this.cantidad -= cantidad;
    }

    public void setIdTipoPieza(int idTipoPieza) {
        this.idTipoPieza = idTipoPieza;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
