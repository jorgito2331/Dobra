<%-- 
    Document   : menu
    Created on : 20/01/2019, 07:05:45 PM
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link href="../Estilos/Menu.css" rel="stylesheet">
    </head>
    <body>
        <div class="menuItem seleccionado" onclick="window.location.replace('../obra/manejar.jsp')">
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
        <% if (session.getAttribute("tipo").equals("ADMIN")) {%>
        <div class="menuItem" onclick="window.location.replace('../parametro/manejar.jsp')">
            <div class="image" id="ajustes"></div>
            <label>Ajustes</label>
        </div>
        <% }%>
    </body>
</html>
