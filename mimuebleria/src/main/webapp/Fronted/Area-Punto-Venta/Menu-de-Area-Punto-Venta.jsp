<%-- 
    Document   : Menu-de-Area-Punto-Venta
    Created on : 4/09/2021, 00:55:47
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MENU AREA PUNTO DE VENTA</title>
    </head>
    <body>
        <h1>MENU AREA PUNTO DE VENTA</h1>
        <a href="Compra-Cliente\Compra-Por-IdMuebleEnsamblado.jsp?Usuario=Mordo777">COMPRA POR ID MUEBLE</a>
        <br>
        <a href="Ventas/Compras-Por-Cliente.jsp">VERIFICAR VENTAS DE UN CLIENTES</a>
        <br>
        <a href="Ventas/Muebles-Ensamblados-Disponibles.jsp">MUEBLES ENSAMBLADOS EN LA SALA</a>
        <br>
        <a href="Ventas/Detalles-Factura-Cliente.jsp">DETALLES DE LA FACTURA DE UN CLIENTE</a>
        <br>
        <a href="Ventas/Ventas-Del-Dia.jsp">VENTAS DEL DIA</a>
        <br>
        <a href="${pageContext.request.contextPath}/index.jsp">pagina anterior</a>
        
    </body>
</html>
