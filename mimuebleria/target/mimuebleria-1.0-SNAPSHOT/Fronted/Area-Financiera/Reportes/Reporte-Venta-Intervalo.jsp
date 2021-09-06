<%-- 
    Document   : Reporte-Venta-Intervalo
    Created on : 4/09/2021, 22:22:45
    Author     : Alex
--%>

<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
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
            <%
                Date fInicio = Date.valueOf(LocalDate.now());
                Date fFin = Date.valueOf(LocalDate.now());
                if (request.getAttribute("FechaInicial") != null && request.getAttribute("FechaFinal") != null) {
                    fInicio = Date.valueOf((String) request.getAttribute("FechaInicial"));
                    fFin = Date.valueOf((String) request.getAttribute("FechaFinal"));
                }%>
            <label>FECHA INICIAL</label>>
            <input type="date" name="fechaInicio" value=<%=fInicio%> required >
            <label>FECHA FINAL</label>>
            <input type="date" name="fechaFin" value=<%=fFin%> required>
            <input type="submit" name="Ordenar" value="Ordenar">
            <table border="3">
                <thead>
                    <tr>
                        <td>ID FACTURA</td>
                        <td>ID PRODUCTO</td>
                        <td>PRODUCTO</td>
                        <td>PRECIO</td>
                        <td>NIT CLIENTE</td>
                        <td>FECHA DE VENTA</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ManagerVenta managerVenta = new ManagerVenta();
                        ArrayList<Venta> ventas = new ArrayList<>();

                        if (request.getAttribute("FechaInicial") == null && request.getAttribute("FechaFinal") == null) {
                            ventas = managerVenta.seleccionarTodo();
                        } else {
                            ventas = managerVenta.seleccionarIntervaloTiempo((String) request.getAttribute("FechaInicial"), (String) request.getAttribute("FechaFinal"));
                        }

                        for (int i = 0; i < ventas.size();
                                i++) {


                    %><tr>
                        <td> <%= ventas.get(i).getFactura().getIdFactura()%></td>
                        <td> <%= ventas.get(i).getMuebleEnsamblado().getIdEnsamblajeMueble()%></td>
                        <td> <%= ventas.get(i).getMuebleEnsamblado().getMueble().getNombreMueble()%></td>
                        <td> <%= ventas.get(i).getPrecioMuebleVendido()%></td>
                        <td> <%= ventas.get(i).getCliente().getNIT()%></td>
                        <td> <%= ventas.get(i).getFechaVenta()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </table>
        <%if (ventas.size() != 0) {
        %>
        <input type="submit" name="Ordenar" value="EXPORTAR_REPORTE_VENTAS">
    </form>
    <%}%>
</body>
</html>
