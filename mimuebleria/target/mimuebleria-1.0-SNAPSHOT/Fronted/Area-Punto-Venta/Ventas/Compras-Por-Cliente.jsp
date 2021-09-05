<%-- 
    Document   : Compras-Por-Cliente
    Created on : 4/09/2021, 18:20:05
    Author     : Alex
--%>

<%@page import="CLASES.Venta"%>
<%@page import="MANAGERS.ManagerVenta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REGISTRO DE COMPRAS POR CLIENTE</title>
    </head>
    <body>
        <h1>REGISTRO DE COMPRAS POR CLIENTE</h1>
        <form action="${pageContext.request.contextPath}/ServletAreaPuntoVenta" method="doPost">
            <%
                String string = "";
                if (!(request.getAttribute("NIT") == null)) {
                    string = (String) request.getAttribute("NIT");
                }
            %>
            <label>NIT:</label>
            <input type="text" name="NIT" value=<%=string%>>
            <input type="submit" name="eleccion" value="VER VENTAS">
            <br>
            <%if (!(request.getAttribute("NIT") == null)) {
                    ManagerVenta managerVenta = new ManagerVenta();
                    ArrayList<Venta> ventasCliente;
            %>
            <table border="3">
                <thead>
                    <tr>
                        <td>ID MUEBLE</td>
                        <td>MUEBLE</td>
                        <td>PRECIO</td>
                        <td>FECHA DE LA VENTA</td>
                        <td>ID FACTURA</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        if (request.getAttribute("Ordenamiento") == null || request.getAttribute("Ordenamiento").equals("ASC")) {
                            ventasCliente = managerVenta.seleccionarVentasClienteASC((String) request.getAttribute("NIT"));
                        } else {
                            ventasCliente = managerVenta.seleccionarVentasClienteDESC((String) request.getAttribute("NIT"));
                        }

                        for (int i = 0; i < ventasCliente.size(); i++) {


                    %><tr>

                        <td> <%= ventasCliente.get(i).getMuebleEnsamblado().getIdEnsamblajeMueble()%></td>
                        <td> <%= ventasCliente.get(i).getMuebleEnsamblado().getMueble().getNombreMueble()%></td>
                        <td> <%= ventasCliente.get(i).getPrecioMuebleVendido()%></td>
                        <td> <%= ventasCliente.get(i).getFechaVenta()%></td>
                        <td> <%= ventasCliente.get(i).getFactura().getIdFactura()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <label>ORDENAR TABLA:</label>
            <select name="eleccion" required>
                <option value="ASC">MENOR A MAYOR</option>
                <option value="DESC">MAYOR A MENOR</option>
            </select>
            <input type="submit" value="Ordenar">
            <%}

            %>            
        </form>
        <a href="${pageContext.request.contextPath}/Fronted/Area-Punto-Venta/Menu-de-Area-Punto-Venta.jsp">pagina anterior</a>
    </body>
</html>
