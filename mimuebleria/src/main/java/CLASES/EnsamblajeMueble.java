/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CLASES;

import java.time.LocalDate;

/**
 *
 * @author Alex
 */
public class EnsamblajeMueble {

    private int idEnsamblajeMueble;
    private LocalDate fechaEnsamblaje;
    private Usuario ensamblador;
    private double costoEnsamblaje;
    private SalaVenta sala;
    private Mueble mueble;

    public EnsamblajeMueble(int idEnsamblajeMueble, LocalDate fechaEnsamblaje, Usuario ensamblador, double costoEnsamblaje, SalaVenta sala, Mueble mueble) {
        this.idEnsamblajeMueble = idEnsamblajeMueble;
        this.fechaEnsamblaje = fechaEnsamblaje;
        this.ensamblador = ensamblador;
        this.costoEnsamblaje = costoEnsamblaje;
        this.sala = sala;
        this.mueble = mueble;
    }

    public int getIdEnsamblajeMueble() {
        return idEnsamblajeMueble;
    }

    public void setIdEnsamblajeMueble(int idEnsamblajeMueble) {
        this.idEnsamblajeMueble = idEnsamblajeMueble;
    }

    public LocalDate getFechaEnsamblaje() {
        return fechaEnsamblaje;
    }

    public void setFechaEnsamblaje(LocalDate fechaEnsamblaje) {
        this.fechaEnsamblaje = fechaEnsamblaje;
    }

    public Usuario getEnsamblador() {
        return ensamblador;
    }

    public void setEnsamblador(Usuario ensamblador) {
        this.ensamblador = ensamblador;
    }

    public double getCostoEnsamblaje() {
        return costoEnsamblaje;
    }

    public void setCostoEnsamblaje(double costoEnsamblaje) {
        this.costoEnsamblaje = costoEnsamblaje;
    }

    public SalaVenta getSala() {
        return sala;
    }

    public void setSala(SalaVenta sala) {
        this.sala = sala;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

}
