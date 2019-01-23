<%-- 
    Document   : crear
    Created on : 11-ago-2018, 22:48:19
    Author     : Jorge-PC
--%>

<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ParametroDTO"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio"%>
<%@page import="sistema.Reportes"%>
<%@page import="org.joda.time.DateMidnight"%>
<%@page import="org.joda.time.Days"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ObraImplementacion"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ObraNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Parámetros</title>        
        <link href="../Estilos/Menu.css" rel="stylesheet">
        <link href="../Estilos/Bread.css" rel="stylesheet">
        <link href="../Estilos/General.css" rel="stylesheet">
        <link href="../Estilos/Tabla.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <body>
        <%
            if (session.getAttribute("tipo") == null) {
                response.sendRedirect("/Garantizar/login.jsp");
            }
            if (!session.getAttribute("tipo").equals("ADMIN")) {
                response.sendRedirect("/Garantizar/obra/manejar.jsp");
            }
            ParametroNegocio parametroNegocio = new ParametroImplementacion();
            ArrayList<ParametroDTO> dTO = parametroNegocio.obtenerParametrosTBL();
        %>
        <div class="menu">
            <div class="menuItem" onclick="window.location.replace('../obra/manejar.jsp')">
                <div class="image" id="obras"></div>
                <label>Obras</label>
            </div>
            <div class="menuItem" onclick="window.location.replace('../contratista/manejar.jsp')">
                <div class="image" id="contratistas"></div>
                <label>Contratistas</label>
            </div>
            <div class="menuItem" onclick="window.location.replace('../funcionario/manejar.jsp')">
                <div class="image" id="funcionarios"></div>
                <label>Funcionarios</label>
            </div>
             <% if ( session.getAttribute("tipo") != null && session.getAttribute("tipo").equals("ADMIN") ) {%>
            <div class="menuItem seleccionado" onclick="window.location.replace('../parametro/manejar.jsp')">
                <div class="image" id="ajustes"></div>
                <label>Ajustes</label>
            </div>
            <% }%>
            <div class="menuItem" onclick="document.getElementById('logout').submit();">
                <div class="image" id="salir"></div>
                <label>Salir</label>
                <form id="logout" method="POST" action="../login">
                    <input type="hidden" name="logout" value="salir">
                </form>
            </div>
        </div>
        <div class="contenedor">
            <div class="bread">
                <a href="manejar.jsp">Ajustes &#8250;</a>
            </div>
            <div class="tabla">
                <table id="table_id" class="display">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Argumento</th>
                            <th>Descripción</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (dTO != null) {
                                for (ParametroDTO cdto : dTO) {%>
                        <tr>
                            <td><%= cdto.getId()%></td>
                            <td><%= cdto.getNombre()%></td>
                            <td>
                                <form class="campoForm" action="../parametro" method="POST">
                                    <input name="nuevoValor" value="<%= cdto.getArgumento()%>"
                                           onkeyup="guardar<%= cdto.getId()%>.style.display = 'flex'"
                                           required="true"
                                           pattern="<%= cdto.getReglas()%>"
                                           title="<%= (cdto.getMensaje() != null) ? cdto.getMensaje() : "" %>">
                                    <button id="guardar<%= cdto.getId()%>" name="id" value="<%= cdto.getId()%>" style="display : none">Guardar</button>
                                </form>
                            </td>
                            <td><%= cdto.getDescripcion()%></td>
                        </tr>
                        <% }
                        } else {
                        %>
                        <tr>
                            <td>No existen datos</td>
                        </tr>
                        <% }
                        %>


                    </tbody>
                </table>
            </div>
            <script
                src="https://code.jquery.com/jquery-3.3.1.min.js"
                integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
            <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
            <script>
                                $(document).ready(function () {
                                    $('#table_id').DataTable();
                                });
            </script>
    </body>
</html>