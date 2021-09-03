/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import CLASES.Tipo;
import Enums.PiezaEnum;
import MANAGERS.ManagerPieza;
import MANAGERS.ManagerTipoPieza;

/**
 *
 * @author Alex
 */
public class ControladorPiezas {

    ManagerTipoPieza tiposPiezas = new ManagerTipoPieza();
    ManagerPieza piezas = new ManagerPieza();

    public void crearTipoPieza(String nombre, double precio) throws NumberFormatException {
        Tipo piezaNueva = new Tipo(0, nombre);
        tiposPiezas.insertarTipoPieza(piezaNueva.getNombre(), piezaNueva.getCantidad());
        piezas.insertarPieza(precio, piezaNueva);
    }

    public void editarPrecioPieza(int idPieza, double precio) {
       piezas.updatePieza(idPieza, ""+precio, PiezaEnum.Costo_Unitario);
    }

    public void editarNombrePieza(Tipo tipo, int idPieza, String nombre) {

    }

    public void eliminarPieza() {

    }
}
