<%-- 
    Document   : Detalles-Factura-Cliente
    Created on : 4/09/2021, 19:47:17
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DETALLES DE FACTURA DE UN CLIENTE, INGRESE EL ID DE LA FACTURA</h1>

        <form action="${pageContext.request.contextPath}/ServletAreaPuntoVenta" method="doPost">
            <label>ID FACTURA:</label>
            <input type="text" name="idFacturaIngresada"/>
            <input type="submit" name="eleccion" value="VER FACTURA">
        </form>
    </body>
</html>
