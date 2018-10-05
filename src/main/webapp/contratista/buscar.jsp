<%-- 
    Document   : buscar
    Created on : 11-ago-2018, 22:48:42
    Author     : Jorge-PC
--%>

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
            ContratistaNegocio contratistaNegocio = new ContratistaImplementacion();
            ArrayList<ContratistaDTO> dTO = contratistaNegocio.obtenerContratistas(request.getParameter("busq"));
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
            <div class="menuItem" onclick="window.location.replace('../parametro/manejar.jsp')">
                <div class="image" id="ajustes"></div>
                <label>Ajustes</label>
            </div>
        </div>
        <div class="contenedor">
            <div class="bread">
                <a href="datos-basicos.jsp">Crear Obra &#8250;</a>
            </div>
            <div class="tabla">
                <table id="table_id" class="display">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Correo</th>
                            <th>Tipo Id</th>
                            <th>Identificación</th>
                            <th>Teléfono</th>
                            <th>Dirección</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (dTO != null) {
                                for (ContratistaDTO cdto : dTO) {%>
                        <tr>
                            <td><%= cdto.getNombre()%></td>
                            <td><%= cdto.getCorreo()%></td>
                            <td><%= cdto.getTipoID()%></td>
                            <td><%= cdto.getIdentificacion()%></td>
                            <td><%= cdto.getTelefono()%></td>
                            <td><%= cdto.getDireccion().getCompleta()%></td>
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