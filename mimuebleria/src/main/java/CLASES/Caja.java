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
public class Caja {

    public double valorCaja;
    public int idCaja;
    public boolean estado;

    public Caja(double valorCaja, int idCaja, boolean estado) {
        this.valorCaja = valorCaja;
        this.idCaja = idCaja;
        this.estado = estado;
    }

    public double getValorCaja() {
        return valorCaja;
    }

    public void setValorCaja(double valorCaja) {
        this.valorCaja = valorCaja;
    }

    public int getIdCaja() {
        return idCaja;
    }

    public void setIdCaja(int idCaja) {
        this.idCaja = idCaja;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
