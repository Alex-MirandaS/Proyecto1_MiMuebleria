/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import CLASES.Cliente;
import CLASES.Factura;
import MANAGERS.ManagerCliente;
import MANAGERS.ManagerFactura;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class ServletAreaPuntoVenta extends HttpServlet {

    ManagerCliente managerCliente = new ManagerCliente();
    ManagerFactura managerFactura = new ManagerFactura();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Direcciones
        String comprasPorCliente = "Fronted/Area-Punto-Venta/Ventas/Compras-Por-Cliente.jsp";
        String factura = "Fronted/Area-Punto-Venta/Compra-Cliente/Factura.jsp";
        //Parametros
        String eleccion = request.getParameter("eleccion");
        String destino = "";
        String nit = request.getParameter("NIT");
        if (eleccion.equalsIgnoreCase("VER VENTAS")) {
            if (nit != "" || !nit.equalsIgnoreCase(null)) {
                Cliente cliente = managerCliente.seleccionarCliente(nit);
                if (cliente != null) {
                    request.setAttribute("NIT", nit);
                    destino = comprasPorCliente;
                }
            }
        } else if (eleccion.equalsIgnoreCase("DESC")) {
            request.setAttribute("OrdenamientoPieza", "DESC");
            request.setAttribute("NIT", nit);
            destino = comprasPorCliente;
        } else if (eleccion.equalsIgnoreCase("ASC")) {
            request.setAttribute("OrdenamientoPieza", "ASC");
            request.setAttribute("NIT", nit);
            destino = comprasPorCliente;
        } else if (eleccion.equalsIgnoreCase("VER FACTURA")) {
            if (request.getParameter("idFacturaIngresada") != null) {
                int idFactura = Integer.parseInt(request.getParameter("idFacturaIngresada"));
                Factura facturaTemp = managerFactura.seleccionarFactura(idFactura);
                if (facturaTemp != null) {
                    request.setAttribute("idFactura", idFactura);
                    destino = factura;
                }
            } else {
                System.out.println("ERROR");
            }
        }
        RequestDispatcher rs = request.getRequestDispatcher(destino);
        rs.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
