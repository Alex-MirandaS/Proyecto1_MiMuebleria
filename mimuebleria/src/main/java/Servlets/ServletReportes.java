/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class ServletReportes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reporteVentaIntervalo = "Fronted/Area-Financiera/Reportes/Reporte-Venta-Intervalo.jsp";
        String reporteGanancias = "Fronted/Area-Financiera/Reportes/Reporte-Ganancias.jsp";
        String reporteMuebleMasVendido = "Fronted/Area-Financiera/Reportes/Reporte-Mueble-Mas-Vendido.jsp";
        String reporteMuebleMenosVendido = "Fronted/Area-Financiera/Reportes/Reporte-Mueble-Menos-Vendido.jsp";
        String reporteUsuarioConMasVentas = "Fronted/Area-Financiera/Reportes/Reporte-Usuario-Mas-Ventas.jsp";

        //Parametros
        String eleccion = request.getParameter("Ordenar");
        String destino = "";

        request.setAttribute("FechaInicial", request.getParameter("fechaInicio"));
        request.setAttribute("FechaFinal", request.getParameter("fechaFin"));
        if (eleccion.equalsIgnoreCase("Ordenar")) {
            destino = reporteVentaIntervalo;
        } else if (eleccion.equalsIgnoreCase("OrdenarGanancias")) {
            destino = reporteGanancias;
        } else if (eleccion.equalsIgnoreCase("OrdenarMuebleMasVendido")) {
            destino = reporteMuebleMasVendido;
        } else if (eleccion.equalsIgnoreCase("OrdenarMuebleMenosVendido")) {
            destino = reporteMuebleMenosVendido;
        } else if (eleccion.equalsIgnoreCase("OrdenarUsuarioConMasVentas")) {
            destino = reporteUsuarioConMasVentas;
        }
        RequestDispatcher rs = request.getRequestDispatcher(destino);
        rs.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
