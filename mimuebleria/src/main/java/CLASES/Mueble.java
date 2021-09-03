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
public class Mueble {

    private int idMueble;
    private double precioVenta;
    private String nombreMueble;

    public Mueble(int idMueble, double precioVenta, String nombreMueble) {
        this.idMueble = idMueble;
        this.precioVenta = precioVenta;
        this.nombreMueble = nombreMueble;
    }

    public int getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(int idMueble) {
        this.idMueble = idMueble;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }
    
    
    
}
