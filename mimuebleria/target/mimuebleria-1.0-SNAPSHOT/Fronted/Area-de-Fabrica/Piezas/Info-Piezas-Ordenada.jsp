<%-- 
    Document   : Informacion-Piezas-Ordenada
    Created on : 2/09/2021, 00:42:27
    Author     : Alex
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="CLASES.Tipo"%>
<%@page import="MANAGERS.ManagerTipoPieza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Piezas Ordenadas</h1>


        <form method="post" action="${pageContext.request.contextPath}/ServletPiezas">
            <select name="direccion" required>
                <option value="ASC">MENOR A MAYOR</option>
                <option value="DESC">MAYOR A MENOR</option>
            </select>
            <input type="submit" value="Ordenar">
        </form>
        <table border="3">
            <thead>
                <tr>
                    <td>ID-TIPO PIEZA</td>
                    <td>NOMBRE</td>
                    <td>CANTIDAD</td>
                </tr>
            </thead>
            <tbody>
                <%
                    ManagerTipoPieza manager = new ManagerTipoPieza();
                    ArrayList<Tipo> piezas = new ArrayList<>();
                    if (request.getAttribute("OrdenamientoPieza") != null) {
                        if (request.getAttribute("OrdenamientoPieza").equals("ASC")) {
                            piezas = manager.seleccionarTodoASC();
                        } else {
                            piezas = manager.seleccionarTodoDESC();
                        }
                    } else {
                        piezas = manager.seleccionarTodoDESC();
                    }

                    for (int i = 0; i < piezas.size(); i++) {

                        Tipo pieza = piezas.get(i);


                %><tr>
                    <td> <%= pieza.getIdTipoPieza()%></td>
                    <td> <%= pieza.getNombre()%></td>
                    <td> <%= pieza.getCantidad()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp">pagina anterior</a>

    </body>
</html>
