<%-- 
    Document   : Menu-Area-Financiera
    Created on : 4/09/2021, 20:35:03
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MENU DE AREA FINANCIERA</title>
    </head>
    <body>
        <h1>MENU DE AREA FINANCIERA</h1>
        <a href="Reportes/Reporte-Venta-Intervalo.jsp">REPORTE VENTA INTERVALO</a>
        <br><!-- comment -->
        <a href="Reportes/Reporte-Ganancias.jsp">REPORTE GANANCIAS</a>
        <br>
        <a href="Reportes/Reporte-Mueble-Mas-Vendido.jsp">REPORTE MUEBLE MAS VENDIDO</a>
        <br>
        <a href="Reportes/Reporte-Usuario-Mas-Ventas.jsp">REPORTE USUARIO CON MAS VENTAS</a>
        <br>
        <a href="${pageContext.request.contextPath}/index.jsp">pagina anterior</a>

    </body>
</html>
