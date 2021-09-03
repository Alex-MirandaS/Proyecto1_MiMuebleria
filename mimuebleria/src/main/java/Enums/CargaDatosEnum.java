/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author Alex
 */
public enum CargaDatosEnum {
    USUARIO(8, "USUARIO("),
    PIEZA(6,"PIEZA("),
    MUEBLE(7,""),
    ENSAMBLAJE_PIEZA(17,"PIEZA("),
    ENSAMBLAR_MUEBLE(17,"PIEZA("),
    CLIENTE(8,"CLIENTE(");

    int caracteres;
    String nombre;

    private CargaDatosEnum(int caracteres, String nombre) {
        this.caracteres = caracteres;
        this.nombre = nombre;
    }

    public int getCaracteres() {
        return caracteres;
    }

    public String getNombre() {
        return nombre;
    }

}
