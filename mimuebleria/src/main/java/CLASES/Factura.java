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
public class Factura {

    private int idFactura;
    private double compraTotal;
    private Cliente cliente;

    public Factura(int idFactura, double compraTotal, Cliente cliente) {
        this.idFactura = idFactura;
        this.compraTotal = compraTotal;
        this.cliente = cliente;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public double getCompraTotal() {
        return compraTotal;
    }

    public void setCompraTotal(double compraTotal) {
        this.compraTotal = compraTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}
