/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import CLASES.EnsamblajePieza;
import CLASES.Pieza;
import CLASES.SalaVenta;
import Enums.SalaVentaEnum;
import Enums.TipoPiezaEnum;
import MANAGERS.ManagerEnsamblajeMueble;
import MANAGERS.ManagerEnsamblajePieza;
import MANAGERS.ManagerMueble;
import MANAGERS.ManagerPieza;
import MANAGERS.ManagerSalaVentas;
import MANAGERS.ManagerTipoPieza;
import MANAGERS.ManagerUsuario;
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
public class ServletMuebles extends HttpServlet {

    //Managers
    ManagerMueble manager = new ManagerMueble();
    ManagerEnsamblajePieza managerEnsamPiezas = new ManagerEnsamblajePieza();
    ManagerTipoPieza managerTipoPieza = new ManagerTipoPieza();
    ManagerEnsamblajeMueble managerEnsamblajeMueble = new ManagerEnsamblajeMueble();
    ManagerUsuario managerUsuario = new ManagerUsuario();
    ManagerPieza managerPieza = new ManagerPieza();
    ManagerSalaVentas managerSalaVentas = new ManagerSalaVentas();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Direcciones
        String ensamblajeMueble = "Fronted/Area-de-Fabrica/Muebles/Ensamblar-Mueble.jsp";
        String mueblesEnsamblados = "Fronted/Area-de-Fabrica/Muebles/Muebles-Ensamblados.jsp";
        String error = "Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp";
        //Parametros
        String eleccion = request.getParameter("eleccion");
        String destino = "";
        if (eleccion.equalsIgnoreCase("ensamblar")) {

            ArrayList<String> piezasNecesarias = new ArrayList<>();
            ArrayList<String> maximos = new ArrayList<>();
            for (int i = 0; i < manager.seleccionarTodo().size(); i++) {
                ArrayList<EnsamblajePieza> mueblePiezaEnsam = managerEnsamPiezas.seleccionarMueble(manager.seleccionarTodo().get(i).getIdMueble());
                int maximo = 0;
                String texto = "";
                boolean sinPiezas = false;
                for (int j = 0; j < mueblePiezaEnsam.size(); j++) {
                    texto += mueblePiezaEnsam.get(j).getPieza().getNombre() + ":" + mueblePiezaEnsam.get(j).getCantidad() + "|";
                    if (mueblePiezaEnsam.get(j).getCantidad() <= managerTipoPieza.seleccionarTipoPieza(mueblePiezaEnsam.get(j).getPieza().getIdTipoPieza()).getCantidad()) {
                        if (!sinPiezas) {

                            int newMaximo = (managerTipoPieza.seleccionarTipoPieza(mueblePiezaEnsam.get(j).getPieza().getIdTipoPieza()).getCantidad()) / (mueblePiezaEnsam.get(j).getCantidad());
                            if (maximo == 0) {
                                maximo += newMaximo;
                            } else if (maximo > newMaximo) {
                                maximo = newMaximo;
                            }
                        } else {
                            maximo = 0;
                        }
                    } else {
                        maximo = 0;
                        sinPiezas = true;
                    }
                }
                piezasNecesarias.add(texto);
                maximos.add("" + maximo);
            }

            request.setAttribute("arrayMuebles", manager.seleccionarTodo());
            request.setAttribute("arrayPiezasNecesarias", piezasNecesarias);
            request.setAttribute("arrayMaximos", maximos);
            destino = ensamblajeMueble;
        } else if (eleccion.equalsIgnoreCase("Ensamblar Mueble")) {
            int cantidadMueblesEnsamblados = Integer.parseInt(request.getParameter("cantidadMueblesEnsamblados"));
            int idMueble = Integer.parseInt(request.getParameter("idMueble"));
            int ensamblesMaximos = Integer.parseInt(request.getParameter("ensamblesMaximos"));
            if (cantidadMueblesEnsamblados <= ensamblesMaximos) {
                for (int i = 0; i < cantidadMueblesEnsamblados; i++) {

                    ArrayList<EnsamblajePieza> mueblePiezaEnsam = managerEnsamPiezas.seleccionarMueble(idMueble);

                    managerEnsamblajeMueble.insertarEnsamblajeMueble(LocalDate.now(), managerUsuario.seleccionarNombre(request.getParameter("nombreUsuario")).getNombreUsuario(),
                            costoEnsamblaje(mueblePiezaEnsam),
                            idMueble,
                            managerSalaVentas.seleccionarPorNombreProducto(manager.seleccionarMueble(idMueble).getNombreMueble()).getIdSalaVenta());

                    SalaVenta sala = managerSalaVentas.seleccionarPorNombreProducto(manager.seleccionarMueble(idMueble).getNombreMueble());
                    managerSalaVentas.updateSalaVentas(sala.getIdSalaVenta(),
                            String.valueOf((sala.getExistencias()) + 1),
                            SalaVentaEnum.Existencias);
                }
                destino = mueblesEnsamblados;
            } else {
                destino = error;
            }
        }else if (eleccion.equalsIgnoreCase("DESC")) {
            request.setAttribute("OrdenamientoMuebleEnsamblado", "DESC");
            destino = mueblesEnsamblados;
        } else if (eleccion.equalsIgnoreCase("ASC")) {
            request.setAttribute("OrdenamientoMuebleEnsamblado", "ASC");
            destino = mueblesEnsamblados;
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

    private double costoEnsamblaje(ArrayList<EnsamblajePieza> mueblePiezaEnsam) {
        double costo = 0;
        for (int i = 0; i < mueblePiezaEnsam.size(); i++) {
            ArrayList<Pieza> piezasEnsamblaJeMueble = managerPieza.seleccionarPorTipo(mueblePiezaEnsam.get(i).getPieza());
            for (int j = 0; j < mueblePiezaEnsam.get(i).getCantidad(); j++) {
                costo += piezasEnsamblaJeMueble.get(j).getCostoUnitario();
                int idTipoPieza = piezasEnsamblaJeMueble.get(j).getTipo().getIdTipoPieza();
                managerTipoPieza.updateTipoPieza(idTipoPieza, "" + (managerTipoPieza.seleccionarTipoPieza(idTipoPieza).getCantidad() - 1), TipoPiezaEnum.Cantidad);
                managerPieza.borrarPieza(piezasEnsamblaJeMueble.get(j).getIdPieza());
            }
        }
        return costo;
    }

}
