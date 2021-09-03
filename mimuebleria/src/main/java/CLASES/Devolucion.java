/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CLASES;

import java.sql.Date;
import java.time.LocalDate;

/**
 * 
 * @author Alex
 */
public class Devolucion {

    private String idDevolucion;
    private Date fechaDevolucion;
    private double dineroDevuelto;
    private double perdidaDinero;
    private Cliente cliente;
    private Caja caja;
    private Factura factura;

    public Devolucion(String idDevolucion, Date fechaDevolucion, double dineroDevuelto, double perdidaDinero, Cliente cliente, Caja caja, Factura factura) {
        this.idDevolucion = idDevolucion;
        this.fechaDevolucion = fechaDevolucion;
        this.dineroDevuelto = dineroDevuelto;
        this.perdidaDinero = perdidaDinero;
        this.cliente = cliente;
        this.caja = caja;
        this.factura = factura;
    }

    public String getIdDevolucion() {
        return idDevolucion;
    }

    public void setIdDevolucion(String idDevolucion) {
        this.idDevolucion = idDevolucion;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }


    public double getDineroDevuelto() {
        return dineroDevuelto;
    }

    public void setDineroDevuelto(double dineroDevuelto) {
        this.dineroDevuelto = dineroDevuelto;
    }

    public double getPerdidaDinero() {
        return perdidaDinero;
    }

    public void setPerdidaDinero(double perdidaDinero) {
        this.perdidaDinero = perdidaDinero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Caja getCaja() {
        return caja;
    }

    public void setCaja(Caja caja) {
        this.caja = caja;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    
    
    
}
