<%-- 
    Document   : Ventas-Del-Dia
    Created on : 4/09/2021, 20:06:28
    Author     : Alex
--%>

<%@page import="CLASES.Venta"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MANAGERS.ManagerVenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>VENTAS DEL DIA</h1>

        <%
            ManagerVenta managerVenta = new ManagerVenta();
            ArrayList<Venta> ventasDelDia = managerVenta.seleccionarFechaVenta(LocalDate.now());
        %>
        <table border="3">
            <thead>
                <tr>
                    <td>ID MUEBLE</td>
                    <td>MUEBLE</td>
                    <td>PRECIO</td>
                    <td>CLIENTE</td>
                    <td>ID FACTURA</td>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < ventasDelDia.size(); i++) {


                %><tr>

                    <td> <%= ventasDelDia.get(i).getMuebleEnsamblado().getIdEnsamblajeMueble()%></td>
                    <td> <%= ventasDelDia.get(i).getMuebleEnsamblado().getMueble().getNombreMueble()%></td>
                    <td> <%= ventasDelDia.get(i).getPrecioMuebleVendido()%></td>
                    <td> <%=ventasDelDia.get(i).getCliente().getNIT()%></td>
                    <td> <%= ventasDelDia.get(i).getFactura().getIdFactura() %></td>
                </tr>
                <%}%>
            </tbody>
        </table>
    </body>
</html>
