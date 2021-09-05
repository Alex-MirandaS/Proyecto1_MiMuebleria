<%-- 
    Document   : Compra-Por-IdMuebleEnsamblado
    Created on : 4/09/2021, 00:58:52
    Author     : Alex
--%>

<%@page import="CLASES.EnsamblajeMueble"%>
<%@page import="MANAGERS.ManagerEnsamblajeMueble"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COMPRA ID MUEBLE</title>
    </head>
    <body>
        <h1>Hello World!</h1>

    <body>

        <%
            if (request.getAttribute("OpcionesNIT") == null || request.getAttribute("OpcionesNIT").equals("conNIT")) {
        %>
        <form action="${pageContext.request.contextPath}/ServletCompraClientes" method="doPost">
            <label>NIT:</label>
            <input type="text" name="NIT"/>
            <input type="submit" name="eleccion" value="NO TENGO NIT">

        <%
        } else if (request.getAttribute("OpcionesNIT").equals("sinNit")) {%>

            <label>NOMBRE:</label>
            <input type="text" name="nombreCliente"/>
            <label>DIRECCIÓN</label>
            <input type="text" name="direccionCliente"/>
            <input type="submit" name="eleccion" value="SI TENGO NIT">

        <%
            }
        %>
        <br>
        <h1>PORFA INGRESE EL ID DEL MUEBLE QUE DESEA COMPRAR</h1>

            <label>ID MUEBLE:</label>
            <input type="number" name="idMuebleEnsamblado"/>
            <%
                if (!(request.getAttribute("idMueblesAnteriores") == null)) {%>
            <input type="hidden" name="mueblesAnteriores" value=<%=(String)request.getAttribute("idMueblesAnteriores")+"-"%>   />
            <%}%>
            <input type="submit" name="eleccion" value="AGREGAR MUEBLE">

            <h1>Muebles</h1>
            <%if (!(request.getAttribute("arrayMuebles") == null)) {
                    ManagerEnsamblajeMueble managerEnsamblajeMueble = new ManagerEnsamblajeMueble();
                    ArrayList<String> idMuebles = (ArrayList<String>) request.getAttribute("arrayMuebles");
            %>
            <table border="3">
                <thead>
                    <tr>
                        <td>ID MUEBLE</td>
                        <td>MUEBLE</td>
                        <td>PRECIO</td>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (int i = 0; i < idMuebles.size(); i++) {

                            EnsamblajeMueble muebleEnsamblado = managerEnsamblajeMueble.seleccionarMuebleEnsamblado(Integer.parseInt(idMuebles.get(i)));


                    %><tr>

                        <td> <%= muebleEnsamblado.getIdEnsamblajeMueble()%></td>
                        <td> <%= muebleEnsamblado.getMueble().getNombreMueble()%></td>
                        <td> <%= muebleEnsamblado.getMueble().getPrecioVenta()%></td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <input type="submit" name="eleccion" value="REALIZAR COMPRA">
        </form>
        <%}%>
        <a href="${pageContext.request.contextPath}/Fronted/Area-Punto-Venta/Menu-de-Area-Punto-Venta.jsp">pagina anterior</a>
    </body>
</body>
</html>
