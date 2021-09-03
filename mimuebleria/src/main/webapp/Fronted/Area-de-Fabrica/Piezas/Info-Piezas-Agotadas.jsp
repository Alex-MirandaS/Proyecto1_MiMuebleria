<%-- 
    Document   : Info-Piezas-Agotadas
    Created on : 2/09/2021, 01:33:20
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
        <h1>PIEZAS POR AGOTARSE</h1>
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

                    piezas = manager.seleccionarAPAgotarse();
                    if (piezas.size() != 0) {

                    }
                    for (int i = 0; i < piezas.size(); i++) {

                        Tipo pieza = piezas.get(i);


                %><tr>
                    <td> <%= pieza.getIdTipoPieza()%></td>
                    <td> <%= pieza.getNombre()%></td>
                    <td> <%= pieza.getCantidad()%></td>
                </tr>
                <%}
                    }%>
            </tbody>
        </table>

        <h1>PIEZAS AGOTADAS</h1>
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
                    ArrayList<Tipo> piezasAgotadas = new ArrayList<>();
                    piezasAgotadas  = manager.seleccionarPAgotadas();

                    for (int i = 0;

                    i< piezasAgotadas.size ();
                    i

                    
                        ++) {

                        Tipo piezasAgotada = piezas.get(i);


                %><tr>
                    <td> <%= piezasAgotada.getIdTipoPieza()%></td>
                    <td> <%= piezasAgotada.getNombre()%></td>
                    <td> <%= piezasAgotada.getCantidad()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/Fronted/Area-de-Fabrica/Menu-de-Fabrica.jsp">pagina anterior</a>
    </body>
</html>
