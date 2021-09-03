<%-- 
    Document   : crearPieza
    Created on : 31/08/2021, 09:08:42
    Author     : Alex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CREAR PIEZA</title>
    </head>
    <body>
        <h1>CREAR PIEZA</h1>

        <form action="${pageContext.request.contextPath}/ServletPiezas" method="doPost">
                <label>Nombre:</label>
                <input type="text" name="nombre"/>

                <label>Precio:</label>
                <input type="number" name="precio"/>

                <input type="submit" name="direccion" value="crearPieza">
            </form>
        
            <a href="../Menu-de-Fabrica.jsp">pagina anterior</a>
    </body>
</html>
