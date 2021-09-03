<%-- 
    Document   : Ensamblar-Mueble
    Created on : 2/09/2021, 10:56:35
    Author     : Alex
--%>

<%@page import="CLASES.Tipo"%>
<%@page import="MANAGERS.ManagerTipoPieza"%>
<%@page import="CLASES.EnsamblajePieza"%>
<%@page import="MANAGERS.ManagerEnsamblajePieza"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CLASES.Mueble"%>
<%@page import="MANAGERS.ManagerMueble"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ENSAMBLAR MUEBLE</title>
    </head>
    <body>
        <h1>ENSAMBLAR MUEBLE</h1>

        <table border="3">
            <thead>
                <tr>
                    <td>ID-MUEBLE</td>
                    <td>NOMBRE</td>
                    <td>PRECIO</td>
                    <td>PIEZAS NECESARIAS</td>
                    <td>CANTIDAD POSIBLE DE ENSAMBLAJE</td>
                    <td>Ensamblaje</td>
                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<Mueble> muebles = (ArrayList<Mueble>) request.getAttribute("arrayMuebles");
                    ArrayList<String> piezasNecesarias = (ArrayList<String>) request.getAttribute("arrayPiezasNecesarias");
                    ArrayList<String> maximos = (ArrayList<String>) request.getAttribute("arrayMaximos");
                    for (int i = 0; i < muebles.size(); i++) {
                        Mueble mueble = muebles.get(i);
                %><tr>

                    <td> <%= mueble.getIdMueble()%></td>
                    <td> <%= mueble.getNombreMueble()%></td>
                    <td> <%= mueble.getPrecioVenta()%></td>
                    <td> <%=piezasNecesarias.get(i)%></td>
                    <td> <%=maximos.get(i)%></td>
                    <td> 

                        <form action="${pageContext.request.contextPath}/ServletMuebles" method="doPost">
                            <label>Cantidad:</label>
                            <input type="number" name="cantidadMueblesEnsamblados"/>
                            <input type="hidden" name="idMueble" value=<%= mueble.getIdMueble()%>>
                            <input type="hidden" name="nombreUsuario" value="Mordo777">
                            <input type="hidden" name="ensamblesMaximos" value=<%=maximos.get(i)%>>
                            <input type="submit" name="eleccion" value="Ensamblar Mueble">
                        </form>
    
                    </td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp">pagina anterior</a>
    </body>
</html>
