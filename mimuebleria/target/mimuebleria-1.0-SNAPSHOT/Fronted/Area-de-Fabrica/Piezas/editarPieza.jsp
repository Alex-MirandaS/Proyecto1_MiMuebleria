<%-- 
    Document   : editarPieza
    Created on : 31/08/2021, 09:42:25
    Author     : Alex
--%>

<%@page import="CLASES.Tipo"%>
<%@page import="MANAGERS.ManagerTipoPieza"%>
<%@page import="CLASES.Pieza"%>
<%@page import="java.util.ArrayList"%>
<%@page import="MANAGERS.ManagerPieza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar PIeza</title>
    </head>
    <body>
        <h1>Editar PIeza</h1>
        <form method="post" action="${pageContext.request.contextPath}/ServletPiezas">
            <%
                ManagerPieza piezas = new ManagerPieza();
                Pieza piezatemp = piezas.seleccionarPieza(Integer.parseInt(request.getParameter("IdPieza")));
            %>
            <h2>PIEZA: <%=piezatemp.getTipo().getNombre()%></h2>
            <br>
            <h2>COSTO: <%=piezatemp.getCostoUnitario()%></h2>
            <br>
            <h2>EDITAR PRECIO</h2>
            <label>Precio:</label>
            <input name="precioCambiado" type="number"/>
            <input type="hidden" name="IdPieza" value=<%=request.getParameter("IdPieza")%>>
            <input type="submit" name="direccion" value="cambiarPrecio">
        </form>
    </body>
</html>
