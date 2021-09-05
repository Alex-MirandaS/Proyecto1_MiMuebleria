<%-- 
    Document   : Reporte-Ganancias
    Created on : 5/09/2021, 04:18:51
    Author     : Alex
--%>

<%@page import="CLASES.Venta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MANAGERS.ManagerVenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REPORTE VENTA POR INTERVALOS DE TIEMPO</title>
    </head>
    <body>
        <form method="post" action="${pageContext.request.contextPath}/ServletReportes">
            <label>FECHA INICIAL</label>>
            <input type="date" name="fechaInicio">
            <label>FECHA FINAL</label>>
            <input type="date" name="fechaFin">
            <input type="submit" name="Ordenar" value="OrdenarUsuarioConMasVentas">
        </form>
        <table border="3">
            <thead>
                <tr>
                    <td>PRODUCTO</td>
                    <td>PRECIO VENTA</td>
                    <td>FECHA DE VENTA</td>
                </tr>
            </thead>
            <tbody>
                <%
                    ManagerVenta managerVenta = new ManagerVenta();
                    ArrayList<Venta> ventas = new ArrayList<>();
                    double gananciasTotales = 0;
                    if (request.getAttribute("FechaInicial") == null && request.getAttribute("FechaFinal") == null) {
                        ventas = managerVenta.seleccionarTodo();
                    } else {
                        ventas = managerVenta.seleccionarMayoresVentasVendedor((String) request.getAttribute("FechaInicial"), (String) request.getAttribute("FechaFinal"));
                    }

                    for (int i = 0; i < ventas.size(); i++) {
                        gananciasTotales += (ventas.get(i).getPrecioMuebleVendido()) - (ventas.get(i).getMuebleEnsamblado().getCostoEnsamblaje());

                %><tr>
                    <td> <%= ventas.get(i).getMuebleEnsamblado().getMueble().getNombreMueble()%></td>
                    <td> <%= ventas.get(i).getPrecioMuebleVendido()%></td>
                    <td> <%= ventas.get(i).getFechaVenta()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <%if (ventas.size() != 0) {
        %>
        <h1>USUARIO CON MAS VENTAS: <%=ventas.get(0).getVendedor().getNombreUsuario() %></h1>
        <%}%>
    </body>
</html>
