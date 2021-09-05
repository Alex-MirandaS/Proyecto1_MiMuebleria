/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import CLASES.Cliente;
import CLASES.EnsamblajeMueble;
import CLASES.Factura;
import CLASES.SalaVenta;
import Enums.EnsamblajeMuebleEnum;
import Enums.FacturaEnum;
import Enums.SalaVentaEnum;
import MANAGERS.ManagerCaja;
import MANAGERS.ManagerCliente;
import MANAGERS.ManagerEnsamblajeMueble;
import MANAGERS.ManagerFactura;
import MANAGERS.ManagerSalaVentas;
import MANAGERS.ManagerVenta;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alex
 */
public class ServletCompraClientes extends HttpServlet {
    //Managers

    ManagerEnsamblajeMueble managerEnsamblajeMueble = new ManagerEnsamblajeMueble();
    ManagerSalaVentas managerSalaVentas = new ManagerSalaVentas();
    ManagerCliente managerCliente = new ManagerCliente();
    ManagerVenta managerVenta = new ManagerVenta();
    ManagerFactura managerFactura = new ManagerFactura();
    ManagerCaja managerCaja = new ManagerCaja();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Direcciones
        String factura = "Fronted/Area-Punto-Venta/Compra-Cliente/Factura.jsp";
        String compraIdMuebleEnsamblado = "Fronted/Area-Punto-Venta/Compra-Cliente/Compra-Por-IdMuebleEnsamblado.jsp";
        //Parametros
        String eleccion = request.getParameter("eleccion");
        String destino = "";

        if (eleccion.equalsIgnoreCase("AGREGAR MUEBLE")) {
            String idMueble = request.getParameter("idMuebleEnsamblado");
            if (idMueble != "" || !idMueble.equalsIgnoreCase(null)) {
                EnsamblajeMueble mueble = managerEnsamblajeMueble.seleccionarMuebleEnsamblado(Integer.parseInt(idMueble));
                if (!(mueble == null)) {
                    ArrayList<String> idmueblesAnterioes = obtenerMueblesAnteriores(request.getParameter("mueblesAnteriores"), idMueble);
                    request.setAttribute("arrayMuebles", idmueblesAnterioes);
                    if (idmueblesAnterioes.size() == 1) {
                        request.setAttribute("idMueblesAnteriores", idMueble);
                    } else {
                        request.setAttribute("idMueblesAnteriores", request.getParameter("mueblesAnteriores") + idMueble);
                    }
                }
            }
            destino = compraIdMuebleEnsamblado;
        } else if (eleccion.equalsIgnoreCase("NO TENGO NIT")) {
            request.setAttribute("OpcionesNIT", "sinNit");
            destino = compraIdMuebleEnsamblado;
        } else if (eleccion.equalsIgnoreCase("SI TENGO NIT")) {
            request.setAttribute("OpcionesNIT", "conNIT");
            destino = compraIdMuebleEnsamblado;
        } else if (eleccion.equalsIgnoreCase("REALIZAR COMPRA")) {
            if (request.getParameter("NIT") != null) {
                Cliente cliente = managerCliente.seleccionarCliente(request.getParameter("NIT"));
                if (cliente != null) {
                    int idFactura = realizarVentas(request.getParameter("mueblesAnteriores"), cliente);
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

    private ArrayList<String> obtenerMueblesAnteriores(String mueblesAnteriores, String nuevoMueble) {
        ArrayList<String> arrayMueblesAnteriores = new ArrayList<>();
        String idMuebletemp = "";
        if (mueblesAnteriores == null) {
            arrayMueblesAnteriores.add(nuevoMueble);
        } else {
            char[] lineaChar = mueblesAnteriores.toCharArray();

            for (int i = 0; i < lineaChar.length; i++) {

                if (lineaChar[i] == '-') {
                    arrayMueblesAnteriores.add(idMuebletemp);
                    idMuebletemp = "";
                } else {
                    idMuebletemp += lineaChar[i];
                }
            }
            arrayMueblesAnteriores.add(nuevoMueble);
        }

        return arrayMueblesAnteriores;
    }

    private int realizarVentas(String idMueblesAComprar, Cliente cliente) {

        ArrayList<String> arrayIdMueblesAComprar = new ArrayList<>();
        String idMuebletemp = "";
        char[] lineaChar = idMueblesAComprar.toCharArray();

        for (int i = 0; i < lineaChar.length; i++) {

            if (lineaChar[i] == '-') {
                arrayIdMueblesAComprar.add(idMuebletemp);
                idMuebletemp = "";
            } else {
                idMuebletemp += lineaChar[i];
            }

        }
        managerFactura.insertarFactura(cliente.getNIT(), 0);
        double compraTotal = 0;
        for (int i = 0; i < arrayIdMueblesAComprar.size(); i++) {
            EnsamblajeMueble muebleEnsamblado = managerEnsamblajeMueble.seleccionarMuebleEnsamblado((Integer.parseInt(arrayIdMueblesAComprar.get(i))));
            Factura factura = managerFactura.seleccionarFactura(managerFactura.seleccionarTodo().size());
            SalaVenta salaVenta = managerSalaVentas.seleccionarPorNombreProducto(muebleEnsamblado.getMueble().getNombreMueble());

            managerCaja.insertarCaja(true, muebleEnsamblado.getMueble().getPrecioVenta());

            managerVenta.insertarVenta(LocalDate.now(), muebleEnsamblado.getMueble().getPrecioVenta(), muebleEnsamblado.getIdEnsamblajeMueble(), salaVenta.getIdSalaVenta(),
                    cliente.getNIT(), factura.getIdFactura(), managerCaja.seleccionarCaja(managerCaja.seleccionarTodo().size()).getIdCaja());
            //Restar de la Base de Datos
             managerCaja.insertarCaja(false, muebleEnsamblado.getCostoEnsamblaje());
            salaVenta.restarExistencia(1);
            managerSalaVentas.updateSalaVentas(salaVenta.getIdSalaVenta(), "" + salaVenta.getExistencias(), SalaVentaEnum.Existencias);

            //aqui iria el cambio en el ensamblaje y la sala de ventas xd
            compraTotal += muebleEnsamblado.getMueble().getPrecioVenta();
            managerEnsamblajeMueble.updateEnsamblajePieza(muebleEnsamblado.getIdEnsamblajeMueble(),""+0, EnsamblajeMuebleEnum.SalaVentas);
        }
        managerFactura.updateFactura(managerFactura.seleccionarTodo().size(), "" + compraTotal, FacturaEnum.CompraTotal);
        return managerFactura.seleccionarFactura(managerFactura.seleccionarTodo().size()).getIdFactura();
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
