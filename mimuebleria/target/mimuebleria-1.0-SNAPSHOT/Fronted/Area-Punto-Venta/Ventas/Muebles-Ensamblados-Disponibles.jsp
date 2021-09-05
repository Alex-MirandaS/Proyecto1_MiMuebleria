<%-- 
    Document   : Muebles-Ensamblados-Disponibles
    Created on : 4/09/2021, 19:24:17
    Author     : Alex
--%>

<%@page import="CLASES.EnsamblajeMueble"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MANAGERS.ManagerEnsamblajeMueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>MUEBLES ENSAMBLADOS DISPONIBLES</h1>

        <table border="3">
            <thead>
                <tr>
                    <td>ID MUEBLE</td>
                    <td>MUEBLE</td>
                    <td>PRECIO</td>
                    <td>FECHA DE ENSAMBLAJE</td>
                </tr>
            </thead>
            <tbody>
                <%
                    ManagerEnsamblajeMueble managerEnsamblajeMueble = new ManagerEnsamblajeMueble();
                    ArrayList<EnsamblajeMueble> mueblesEnSV = managerEnsamblajeMueble.seleccionarMueblesEnsambladosEnSV();
                    for (int i = 0; i < mueblesEnSV.size(); i++) {


                %><tr>

                    <td> <%= mueblesEnSV.get(i).getIdEnsamblajeMueble() %></td>
                    <td> <%= mueblesEnSV.get(i).getMueble().getNombreMueble() %></td>
                    <td> <%= mueblesEnSV.get(i).getMueble().getPrecioVenta() %></td>
                    <td> <%=mueblesEnSV.get(i).getFechaEnsamblaje() %></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-Punto-Venta/Menu-de-Area-Punto-Venta.jsp">pagina anterior</a>

    </body>
</html>
