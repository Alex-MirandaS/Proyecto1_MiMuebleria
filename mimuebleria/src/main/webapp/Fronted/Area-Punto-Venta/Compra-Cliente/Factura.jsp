<%-- 
    Document   : Factura
    Created on : 4/09/2021, 09:42:34
    Author     : Alex
--%>

<%@page import="CLASES.Venta"%>
<%@page import="CLASES.EnsamblajeMueble"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MANAGERS.ManagerEnsamblajeMueble"%>
<%@page import="CLASES.Cliente"%>
<%@page import="MANAGERS.ManagerVenta"%>
<%@page import="CLASES.Factura"%>
<%@page import="MANAGERS.ManagerFactura"%>
<%@page import="MANAGERS.ManagerCliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Factura</title>
    </head>
    <body>
        <h1>factura</h1>
        <%
            ManagerCliente managerCliente = new ManagerCliente();
            ManagerFactura managerFactura = new ManagerFactura();
            ManagerVenta managerVenta = new ManagerVenta();
            ManagerEnsamblajeMueble managerEnsamblajeMueble = new ManagerEnsamblajeMueble();

            Factura factura = managerFactura.seleccionarFactura(Integer.parseInt(String.valueOf(request.getAttribute("idFactura"))));
            ArrayList<Venta> ventasClienteFactura = managerVenta.seleccionarVentasFactura(factura.getIdFactura());
            Cliente cliente = managerCliente.seleccionarCliente(managerVenta.seleccionarVentasFactura(factura.getIdFactura()).get(0).getCliente().getNIT());

        %>
        <h1>DATOS DEL CLIENTE</h1>
        <br>
        <h1>NOMBRE: <%=cliente.getNombre()%></h1>
        <br>
        <h1>NIT: <%=cliente.getNIT()%></h1>
        <br>
        <h1>DIRECCION: <%=cliente.getDireccion()%></h1>
        <br>
        <h1>DEPARTAMENTO: <%=cliente.getDepartamento()%></h1>
        <br>
        <h1>MUNICIPIO: <%=cliente.getMunicipio()%></h1>
        <br>

        <table border="3">
            <thead>
                <tr>
                    <td>ID MUEBLE ENSAMBLADO</td>
                    <td>MUEBLE</td>
                    <td>PRECIO</td>
                    <td>MONTO</td>
                </tr>
            </thead>
            <tbody>
                <%
                    double monto = 0;
                    for (int i = 0; i < ventasClienteFactura.size(); i++) {

                        monto += ventasClienteFactura.get(i).getPrecioMuebleVendido();
                %><tr>
                    <td> <%= ventasClienteFactura.get(i).getMuebleEnsamblado().getIdEnsamblajeMueble()%></td>
                    <td> <%= ventasClienteFactura.get(i).getMuebleEnsamblado().getMueble().getNombreMueble()%></td>
                    <td> <%= ventasClienteFactura.get(i).getPrecioMuebleVendido()%></td>
                    <td> <%=monto%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-Punto-Venta/Menu-de-Area-Punto-Venta.jsp">pagina anterior</a>
    </body>
</html>
