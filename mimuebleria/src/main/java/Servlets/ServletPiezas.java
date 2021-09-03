/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import CLASES.Pieza;
import CLASES.Tipo;
import ClasesPredeterminadas.Conexion;
import Enums.PiezaEnum;
import Enums.TipoPiezaEnum;
import MANAGERS.ManagerPieza;
import MANAGERS.ManagerTipoPieza;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class ServletPiezas extends HttpServlet {

    Conexion con = new Conexion();
    ManagerPieza piezas = new ManagerPieza();
    ManagerTipoPieza tipoPieza = new ManagerTipoPieza();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String listaPiezas = "Fronted/Area-de-Fabrica/Piezas/Info-Piezas.jsp";
        String listaExistenciaPiezas = "Fronted/Area-de-Fabrica/Piezas/Info-Piezas-Ordenada.jsp";
        String direccion = request.getParameter("direccion");
        String idPieza = request.getParameter("IdPieza");
        String idTipoPieza = request.getParameter("IdTipoPieza");
        String destino = "";
        if (direccion.equalsIgnoreCase("lista")) {
            destino = listaPiezas;
        } else if (direccion.equalsIgnoreCase("crearPieza")) {
            String nombre = request.getParameter("nombre");
            double precio = Double.parseDouble(request.getParameter("precio"));
            if (tipoPieza.seleccionarTipoPiezaNombre(nombre) == null) {
                tipoPieza.insertarTipoPieza(nombre,1);
                piezas.insertarPieza(precio, tipoPieza.seleccionarTipoPiezaNombre(nombre));
            }else{
                tipoPieza.updateTipoPieza(tipoPieza.seleccionarTipoPiezaNombre(nombre).getIdTipoPieza(), ""+(tipoPieza.seleccionarTipoPiezaNombre(nombre).getCantidad()+1), TipoPiezaEnum.Cantidad);
                piezas.insertarPieza(precio, tipoPieza.seleccionarTipoPiezaNombre(nombre));
            }
            destino = listaPiezas;
        } else if (direccion.equalsIgnoreCase("cambiarPrecio")) {
            String nuevoPrecio = request.getParameter("precioCambiado");
            piezas.updatePieza(Integer.parseInt(idPieza), nuevoPrecio, PiezaEnum.Costo_Unitario);
            destino = listaPiezas;
        } else if (direccion.equalsIgnoreCase("DESC")) {
            request.setAttribute("OrdenamientoPieza", "DESC");
            destino = listaExistenciaPiezas;
        } else if (direccion.equalsIgnoreCase("ASC")) {
            request.setAttribute("OrdenamientoPieza", "ASC");
            destino = listaExistenciaPiezas;
        } else {
            piezas.borrarPieza(Integer.parseInt(idPieza));
            tipoPieza.seleccionarTipoPieza(Integer.parseInt(idTipoPieza)).restarUnidades(1);
            destino = listaPiezas;
        }

        RequestDispatcher rs = request.getRequestDispatcher(destino);
        rs.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
