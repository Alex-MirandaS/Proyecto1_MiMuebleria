<%-- 
    Document   : Ensamblar-Muebles
    Created on : 2/09/2021, 10:44:24
    Author     : Alex
--%>

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
        <form method="post" action="${pageContext.request.contextPath}/ServletMuebles">
            <select name="eleccion" required>
                <option value="ASC">MENOR A MAYOR</option>
                <option value="DESC">MAYOR A MENOR</option>
            </select>
            <input type="submit" value="Ordenar">
        </form>
        <table border="3">
            <thead>
                <tr>
                    <td>ID MUEBLE ENSAMBLADO</td>
                    <td>MUEBLE</td>
                    <td>ENSAMBLADOR</td>
                    <td>FECHA-ENSAMBLAJE</td>
                    <td>COSTO</td>
                </tr>
            </thead>
            <tbody>
                <%
                    ManagerEnsamblajeMueble manager = new ManagerEnsamblajeMueble();
                    ArrayList<EnsamblajeMueble> mueblesEnsamblados = new ArrayList<>();
                    if (request.getAttribute("OrdenamientoMuebleEnsamblado") != null) {
                        if (request.getAttribute("OrdenamientoMuebleEnsamblado").equals("ASC")) {
                            mueblesEnsamblados = manager.seleccionarTodoASC();
                        } else {
                            mueblesEnsamblados = manager.seleccionarTodoDESC();
                        }
                    } else {
                        mueblesEnsamblados = manager.seleccionarTodo();
                    }

                    for (int i = 0; i < mueblesEnsamblados.size(); i++) {

                        EnsamblajeMueble muebleEnsamblado = mueblesEnsamblados.get(i);


                %><tr>
                    <td> <%= muebleEnsamblado.getIdEnsamblajeMueble() %></td>
                    <td> <%= muebleEnsamblado.getMueble().getNombreMueble()%></td>
                    <td> <%= muebleEnsamblado.getEnsamblador().getNombreUsuario()%></td>
                    <td> <%= muebleEnsamblado.getFechaEnsamblaje()%></td>
                    <td> <%= muebleEnsamblado.getCostoEnsamblaje()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp">pagina anterior</a>
    </body>
</html>
