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
public class EnsamblajePieza {

    private int idEnsamblajePiezas;
    private int cantidad;
    private Tipo tipoPieza;
    private Mueble mueble;

    public EnsamblajePieza(int idEnsamblajePiezas, int cantidad, Tipo tipoPieza, Mueble mueble) {
        this.idEnsamblajePiezas = idEnsamblajePiezas;
        this.cantidad = cantidad;
        this.tipoPieza = tipoPieza;
        this.mueble = mueble;
    }

    public int getIdEnsamblajePiezas() {
        return idEnsamblajePiezas;
    }

    public void setIdEnsamblajePiezas(int idEnsamblajePiezas) {
        this.idEnsamblajePiezas = idEnsamblajePiezas;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Tipo getPieza() {
        return tipoPieza;
    }

    public void setPieza(Tipo pieza) {
        this.tipoPieza = pieza;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

}
