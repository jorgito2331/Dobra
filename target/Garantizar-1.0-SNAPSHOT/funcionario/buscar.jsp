<%-- 
    Document   : buscar
    Created on : 11-ago-2018, 22:48:42
    Author     : Jorge-PC
--%>

<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.FuncionarioImplementacion"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.FuncionarioNegocio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ContratistaImplementacion"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ContratistaNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscar contratista</title>        
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
            FuncionarioNegocio funcionarioNegocio = new FuncionarioImplementacion();
            ArrayList<FuncionarioDTO> dTO = funcionarioNegocio.obtenerTodos();
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
            <div class="menuItem seleccionado" onclick="window.location.replace('../funcionario/manejar.jsp')">
                <div class="image" id="funcionarios"></div>
                <label>Funcionarios</label>
            </div>
            <% if ( session.getAttribute("tipo") != null && session.getAttribute("tipo").equals("ADMIN") ) {%>
            <div class="menuItem" onclick="window.location.replace('../parametro/manejar.jsp')">
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
                <a href="manejar.jsp">Funcionario &#8250;</a>
            </div>
            <div class="tabla">
                <table id="table_id" class="display">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombres</th>
                            <th>Apellidos</th>
                            <th>Rol</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (dTO != null) {
                                for (FuncionarioDTO cdto : dTO) {%>
                        <tr>
                            <td><%= cdto.getId()%></td>
                            <td><%= cdto.getPriNombre() + " " + ( (cdto.getSegNombre()!= null ) ? cdto.getSegNombre() : "" )%></td>
                            <td><%= cdto.getPriApellido() + " " + ( (cdto.getSegApellido() != null ) ? cdto.getSegApellido() : "" ) %></td>
                            <td><%= cdto.getTipo()%></td>
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