<%-- 
    Document   : index
    Created on : 31/08/2021, 18:31:47
    Author     : Alex
--%>

<%@page import="ClasesPredeterminadas.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%Conexion conexion = new Conexion();%>
        <a href="ServletPiezas?direccion=lista">Listapiezas</a>
        <a href="Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp">Menu Area de Fabrica</a>
        <a href="Fronted/Area-Punto-Venta/Menu-de-Area-Punto-Venta.jsp">Menu Area Punto de Venta</a>
    </body>
</html>
