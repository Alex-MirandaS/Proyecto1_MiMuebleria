<%-- 
    Document   : Ensamblar-Muebles
    Created on : 2/09/2021, 10:44:24
    Author     : Alex
--%>

<%@page import="CLASES.SalaVenta"%>
<%@page import="MANAGERS.ManagerSalaVentas"%>
<%@page import="CLASES.EnsamblajeMueble"%>
<%@page import="MANAGERS.ManagerEnsamblajeMueble"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CLASES.Mueble"%>
<%@page import="MANAGERS.ManagerMueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MUEBLES ENSAMBLADOS</title>
    </head>
    <body>
        <h1>MUEBLES ENSAMBLADOS</h1>
        <table border="3">
            <thead>
                <tr>
                    <td>MUEBLE</td>
                    <td>PRECIO</td>
                    <td>EXISTENCIAS</td>
                </tr>
            </thead>
            <tbody>
                <%
                    ManagerSalaVentas manager = new ManagerSalaVentas();
                    ArrayList<SalaVenta> salaVentas = new ArrayList<>();
                    salaVentas = manager.seleccionarTodo();
                    for (int i = 0; i < salaVentas.size(); i++) {

                        SalaVenta muebleSala = salaVentas.get(i);


                %><tr>

                    <td> <%= muebleSala.getTipoMueble()%></td>
                    <td> <%= muebleSala.getPrecioMueble() %></td>
                    <td> <%= muebleSala.getExistencias() %></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp">pagina anterior</a>
    </body>
</html>
