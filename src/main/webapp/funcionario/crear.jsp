<%-- 
    Document   : crear
    Created on : 11-ago-2018, 22:48:19
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
        <title>Crear Funcionario</title>
        <link href="../Estilos/Menu.css" rel="stylesheet">
        <link href="../Estilos/Bread.css" rel="stylesheet">
        <link href="../Estilos/General.css" rel="stylesheet">
        <link href="../Estilos/Formulario.css" rel="stylesheet">
    </head>
    <body>
        <%
            if (session.getAttribute("tipo") == null) {
                response.sendRedirect("/Garantizar/login.jsp");
            }
            ArrayList<ParametroDTO> tipoId;
            ParametroNegocio negocio = new ParametroImplementacion();
            tipoId = negocio.obtenerParametros("2");
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
                <a href="crear.jsp">Crear Funcionario &#8250;</a>
            </div>
            <form class="formulario" action="../funcionario" method="POST">
                <div class="campo">
                    <label>Id</label>
                    <input required="true"  name="nombre" id="id" readonly="true">
                </div>
                <div class="campo">
                    <label>Clave</label>
                    <input required="true"  type="password" name="clave" id="clave" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$" title="La clave debe tener 8 caracteres, una letra, un número y un caracter especial">
                </div>      
                <div class="campo">
                    <label>Primer nombre</label>
                    <input required="true"  name="priNom" id="priNom" onkeyup="document.getElementById('id').value = document.getElementById('priNom').value + '.' + document.getElementById('priApe').value">
                </div>
                <div class="campo">
                    <label>Segundo nombre</label>
                    <input name="segNom" id="segNom">
                </div>
                <div class="campo">
                    <label>Primer Apellido</label>
                    <input required="true" name="priApe" id="priApe" onkeyup="document.getElementById('id').value = document.getElementById('priNom').value + '.' + document.getElementById('priApe').value">
                </div>
                <div class="campo">
                    <label>Segundo Apellido</label>
                    <input name="segApe" id="segApe">
                </div>
                <div class="control">
                    <a class="cancelar" id="cancelarBtn" href="manejar.jsp">Cancelar</a>
                    <button type="submit" class="guardar" id="GuardarForm" name="guar">Guardar</button>
                </div>
            </form>
        </div>
        <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
    </body>
</html>
