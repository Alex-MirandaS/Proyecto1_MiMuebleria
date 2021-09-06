<%-- 
    Document   : Reporte-Ganancias
    Created on : 5/09/2021, 04:18:51
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
            <input type="submit" name="Ordenar" value="OrdenarMuebleMasVendido">
        <table border="3">
            <thead>
                <tr>
                    <td>ID FACTURA</td>
                    <td>ID PRODUCTO</td>
                    <td>PRODUCTO</td>
                    <td>COSTO</td>
                    <td>PRECIO VENTA</td>
                    <td>GANANCIA</td>
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
                        ventas = managerVenta.seleccionarVentasMuebleMasVendido((String) request.getAttribute("FechaInicial"), (String) request.getAttribute("FechaFinal"));
                    }

                    for (int i = 0; i < ventas.size(); i++) {

                %><tr>
                    <td> <%= ventas.get(i).getFactura().getIdFactura()%></td>
                    <td> <%= ventas.get(i).getMuebleEnsamblado().getIdEnsamblajeMueble()%></td>
                    <td> <%= ventas.get(i).getMuebleEnsamblado().getMueble().getNombreMueble()%></td>
                    <td> <%= ventas.get(i).getMuebleEnsamblado().getCostoEnsamblaje()%></td>
                    <td> <%= ventas.get(i).getPrecioMuebleVendido()%></td>
                    <td> <%= (ventas.get(i).getPrecioMuebleVendido()) - (ventas.get(i).getMuebleEnsamblado().getCostoEnsamblaje())%></td>
                    <td> <%= ventas.get(i).getCliente().getNIT()%></td>
                    <td> <%= ventas.get(i).getFechaVenta()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <%if (ventas.size() != 0) {
        %>
        <h1>MUEBLE MAS VENDIDO: <%=ventas.get(0).getMuebleEnsamblado().getMueble().getNombreMueble()%></h1>
        <input type="submit" name="Ordenar" value="EXPORTAR_REPORTE_MUEBLE_MAS_VENDIDO">
        </form>
        <%}%>
    </body>
</html>
