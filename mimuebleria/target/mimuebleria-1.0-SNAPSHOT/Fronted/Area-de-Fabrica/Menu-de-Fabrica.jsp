<%-- 
    Document   : Menu de Fabrica
    Created on : 31/08/2021, 05:57:59
    Author     : Alex
--%>

<%@page import="ClasesPredeterminadas.Conexion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú de Fabrica</title>
    </head>
    <body>
        <% Conexion con = new Conexion();%>
        <strong>
            <h1>BIENVENIDO AL MENÚ DE FABRICA</h1>
        </strong>
        <h4>Elija una opcion</h4>
        <br>

        <a href="Piezas\crearPieza.jsp">CREAR PIEZAS</a>
        <br>
        <a href="Piezas\Info-Piezas.jsp">INFORMACIÓN DE PIEZAS</a>
        <br>
        <a href="Piezas\Info-Piezas-Ordenada.jsp">EXISTENCIA DE PIEZAS</a>
        <br>
        <a href="Piezas\Info-Piezas-Agotadas.jsp">PIEZAS AGOTADAS</a>
        <br>
        <a href="../../ServletMuebles?eleccion=ensamblar">Ensamblar Mueble</a>
        <br>
        <a href="Muebles/Muebles-Ensamblados.jsp">MUEBLES ENSAMBLADOS</a>
        <br>
        <a href="Muebles/Muebles-Sala-Ventas.jsp">MUEBLES SALA VENTAS</a>
        <br><!-- comment -->
        <a href="${pageContext.request.contextPath}/index.jsp">pagina anterior</a>
    </body>
</html>
