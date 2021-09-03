<%-- 
    Document   : Información de Piezas
    Created on : 31/08/2021, 09:57:55
    Author     : Alex
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="CLASES.Pieza"%>
<%@page import="MANAGERS.ManagerPieza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Información de Piezas</title>
    </head>
    <body>
        <h1>Lista de Piezas</h1>
            <table border="3">
                <thead>
                    <tr>
                        <td>ID-PIEZA</td>
                        <td>CostoUnitario</td>
                        <td>TipoPieza</td>
                        <td>Opciones</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ManagerPieza manager = new ManagerPieza();
                        ArrayList<Pieza> piezas = new ArrayList<>();
                        piezas = manager.seleccionarTodo();
                        for (int i = 0; i < piezas.size(); i++) {

                            Pieza pieza = piezas.get(i);


                    %><tr>

                        <td> <%= pieza.getIdPieza()%></td>
                        <td> <%= pieza.getCostoUnitario()%></td>
                        <td> <%= pieza.getTipo().getNombre() %></td>
                        <td> 
                            <a href="${pageContext.request.contextPath}/Fronted/Area-de-Fabrica/Piezas/editarPieza.jsp?IdPieza=<%=pieza.getIdPieza()%>">Editar</a>
                            <a href="/mimuebleria/ServletPiezas?direccion=eliminar&IdPieza=<%=pieza.getIdPieza()%>&IdTipoPieza=<%=pieza.getTipo().getIdTipoPieza()%>">Eliminar</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp">pagina anterior</a>
    </body>
</html>
