<%-- 
    Document   : manejar
    Created on : 27-sep-2018, 20:59:40
    Author     : Jorge-PC
--%>

<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ContratistaImplementacion"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ContratistaNegocio"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ParametroDTO"%>
<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear contratista</title>
        <link href="../Estilos/Menu.css" rel="stylesheet">
        <link href="../Estilos/Bread.css" rel="stylesheet">
        <link href="../Estilos/General.css" rel="stylesheet">
        <link href="../Estilos/Formulario.css" rel="stylesheet">
    </head>
    <body>
        <div class="menu">
            <div class="menuItem">
                <div class="image" id="obras"></div>
                <label>Obras</label>
            </div>
            <div class="menuItem">
                <div class="image" id="contratistas"></div>
                <label>Contratistas</label>
            </div>
            <div class="menuItem">
                <div class="image" id="funcionarios"></div>
                <label>Funcionarios</label>
            </div>
            <div class="menuItem">
                <div class="image" id="ajustes"></div>
                <label>Ajustes</label>
            </div>
        </div>
        <div class="contenedor">
            <div class="bread">
                <a href="manejar.jsp">Contratista &#8250;</a>
                <a href="crear.jsp">Crear Contratista &#8250;</a>
            </div>
            <form class="formulario" action="buscar.jsp" method="POST">              
                <div class="campo">
                    <label>Criterio</label>
                    <select id="busq" name="busq">
                        <option value="4">Todos</option>
                        <option value="0">En curso</option>
                        <option value="1">Finalizada</option>
                        <option value="2">Desfazada</option>
                        <option value="3">Específica</option>
                    </select>
                </div>
                <div class="campo">
                    <label>Nombre</label>
                    <input name="nombre" id="nombre">
                </div>
                <div class="control">
                    <button type="submit" class="cancelar" id="cancelarBtn" onclick="$('#cancelarBtn').val('cancelar')">Cancelar</button>
                    <button type="submit" class="guardar" id="GuardarForm" name="guarManejar" value="guardar">Guardar</button>
                </div>
            </form>
        </div>
        <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
    </body>
</html>