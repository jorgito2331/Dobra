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
        <title>Crear contratista</title>
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
            <div class="menuItem seleccionado" onclick="window.location.replace('../contratista/manejar.jsp')">
                <div class="image" id="contratistas"></div>
                <label>Contratistas</label>
            </div>
            <div class="menuItem" onclick="window.location.replace('../funcionario/manejar.jsp')">
                <div class="image" id="funcionarios"></div>
                <label>Funcionarios</label>
            </div>
            <% if (session.getAttribute("tipo") != null && session.getAttribute("tipo").equals("ADMIN")) {%>
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
                <a href="manejar.jsp">Contratista &#8250;</a>
                <a href="crear.jsp">Crear Contratista &#8250;</a>
            </div>
            <form class="formulario" action="../contratista" method="POST" autocomplete="off">
                <div class="campo">
                    <label>Nombre</label>
                    <input name="nombre" id="nombre" required="true">
                </div>
                <div class="campo">
                    <label>Correo</label>
                    <input type="email" name="correo" id="correo" required="true" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" title="El correo tiene un formato incorrecto">
                </div>                
                <div class="campo">
                    <label>Tipo de identificación</label>
                    <select name="tipoId" required="true">
                        <option value="0">Seleccione</option>
                        <% for (ParametroDTO parametro : tipoId) {
                        %>
                        <option value="<%= parametro.getId()%>"><%= parametro.getNombre()%></option>
                        <% }%>
                    </select>
                </div>
                <div class="campo">
                    <label>Identificación</label>
                    <input name="identificacion" id="identificacion" required="true">
                </div>
                <div class="campo">
                    <label>Teléfono</label>
                    <input name="telefono" id="telefono" required="true" pattern="^[0-9]{7,13}$">
                </div>
                <div class="campo">
                    <label>Dirección</label>
                    <input onclick="$('#direccionModal').addClass('mostrar')" name="completa" id="dirr" required="true" readonly>
                </div>
                <div class="control">
                    <a class="cancelar" id="cancelarBtn" href="manejar.jsp">Cancelar</a>
                    <button type="submit" class="guardar" id="GuardarForm" name="guar">Guardar</button>
                </div>
            </form>
        </div>
        <div class="modal" id="direccionModal">
            <div class="direccion">
                <div class="campo">
                    <label>Tipo de via</label>
                    <select name="TVia" id="tipVia">
                        <option value="0">Seleccione</option>
                        <option value="Calle">Calle</option>
                        <option value="Carrera">Carrera</option>
                        <option value="Transversal">Transversal</option>
                        <option value="Diagonal">Diagonal</option>
                        <option value="Avenida">Avenida</option>
                    </select>
                </div>
                <div class="campo">
                    <label>Número de via</label>
                    <input type="number" maxlength="" pattern="" id="numVia" value="">
                </div>
                <div class="campo">
                    <label>Sufijo de via</label>
                    <select id="sufVia">
                        <option value="0">Seleccione</option>
                        <%
                            for (int i = 0; i <= 25; i++) {
                        %>
                        <option value="<%= (char) ('A' + i)%>"><%= (char) ('A' + i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="campo">
                    <label>Cardinalidad de via</label>
                    <select id="cardVia">
                        <option value="0">Seleccione</option>
                        <option value="Norte">Norte</option>
                        <option value="Este">Este</option>
                        <option value="Sur">Sur</option>
                        <option value="Oeste">Oeste</option>
                        <option value="Nordeste">Nordeste</option>
                        <option value="Sudeste">Sudeste</option>
                        <option value="Sudoeste">Sudoeste</option>
                        <option value="Noroeste">Noroeste</option>
                    </select>
                </div>
                <div class="campo">
                    <label>Primer número</label>
                    <input type="number" maxlength="" pattern="" id="priNum" value="">
                </div>
                <div class="campo">
                    <label>Sufijo primer número</label>
                    <select id="sufPriNum">
                        <option value="0">Seleccione</option>
                        <%
                            for (int i = 0; i <= 25; i++) {
                        %>
                        <option value="<%= (char) ('A' + i)%>"><%= (char) ('A' + i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="campo">
                    <label>Cardinalidad primer número</label>
                    <select id="cardPriNum">
                        <option value="0">Seleccione</option>
                        <option value="Norte">Norte</option>
                        <option value="Este">Este</option>
                        <option value="Sur">Sur</option>
                        <option value="Oeste">Oeste</option>
                        <option value="Nordeste">Nordeste</option>
                        <option value="Sudeste">Sudeste</option>
                        <option value="Sudoeste">Sudoeste</option>
                        <option value="Noroeste">Noroeste</option>
                    </select>
                </div>
                <div class="campo">
                    <label>Segundo número</label>
                    <input type="number" maxlength="" pattern="" id="segNum" value="">
                </div>
                <div class="campo">
                    <label>Sufijo segundo número</label>
                    <select id="sufSegNum">
                        <option value="0">Seleccione</option>
                        <%
                            for (int i = 0; i <= 25; i++) {
                        %>
                        <option value="<%= (char) ('A' + i)%>"><%= (char) ('A' + i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="control">
                    <button class="cancelar" onclick="$('#direccionModal').removeClass('mostrar')">Cancelar</button>
                    <button class="guardar" onclick="guardar()" id="guardar">Guardar</button>
                </div>
            </div>
        </div>
        <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
        crossorigin="anonymous"></script>
        <script>
                        var direccion = "";
                        direccion = direccion + (($('#tipVia').val() === "0") ? "" : $('#tipVia').val() + " ");
                        direccion = direccion + (($('#numVia').val() === "0") ? "" : $('#numVia').val() + " ");
                        direccion = direccion + (($('#sufVia').val() === "0") ? "" : $('#sufVia').val() + " ");
                        direccion = direccion + (($('#cardVia').val() === "0") ? "" : $('#cardVia').val() + " ");
                        direccion = direccion + (($('#priNum').val() === "0") ? "" : $('#priNum').val() + " ");
                        direccion = direccion + (($('#sufPriNum').val() === "0") ? "" : $('#sufPriNum').val() + " ");
                        direccion = direccion + (($('#cardPriNum').val() === "0") ? "" : $('#cardPriNum').val() + " ");
                        direccion = direccion + (($('#segNum').val() === "0") ? "" : $('#segNum').val() + " ");
                        direccion = direccion + (($('#sufSegNum').val() === "0") ? "" : $('#sufSegNum').val() + " ");
                        $('#dirr').val(direccion);
                        function guardar() {
                            var direccion = "";
                            direccion = direccion + (($('#tipVia').val() === "0") ? "" : $('#tipVia').val() + " ");
                            direccion = direccion + (($('#numVia').val() === "0") ? "" : $('#numVia').val() + " ");
                            direccion = direccion + (($('#sufVia').val() === "0") ? "" : $('#sufVia').val() + " ");
                            direccion = direccion + (($('#cardVia').val() === "0") ? "" : $('#cardVia').val() + " ");
                            direccion = direccion + (($('#priNum').val() === "0") ? "" : $('#priNum').val() + " ");
                            direccion = direccion + (($('#sufPriNum').val() === "0") ? "" : $('#sufPriNum').val() + " ");
                            direccion = direccion + (($('#cardPriNum').val() === "0") ? "" : $('#cardPriNum').val() + " ");
                            direccion = direccion + (($('#segNum').val() === "0") ? "" : $('#segNum').val() + " ");
                            direccion = direccion + (($('#sufSegNum').val() === "0") ? "" : $('#sufSegNum').val() + " ");
                            $('#dirr').val(direccion);
                            direccion = "";
                            direccion = direccion + (($('#tipVia').val() == "0") ? "0@" : $('#tipVia').val()) + " ";
                            direccion = direccion + (($('#numVia').val() == "0") ? "0@" : $('#numVia').val()) + " ";
                            direccion = direccion + (($('#sufVia').val() == "0") ? "0@" : $('#sufVia').val()) + " ";
                            direccion = direccion + (($('#cardVia').val() == "0") ? "0@" : $('#cardVia').val()) + " ";
                            direccion = direccion + (($('#priNum').val() == "0") ? "0@" : $('#priNum').val()) + " ";
                            direccion = direccion + (($('#sufPriNum').val() == "0") ? "0@" : $('#sufPriNum').val()) + " ";
                            direccion = direccion + (($('#cardPriNum').val() == "0") ? "0@" : $('#cardPriNum').val()) + " ";
                            direccion = direccion + (($('#segNum').val() == "0") ? "0@" : $('#segNum').val()) + " ";
                            direccion = direccion + (($('#sufSegNum').val() == "0") ? "0@" : $('#sufSegNum').val()) + " ";
                            console.log(direccion);
                            $('#GuardarForm').val(direccion);
                            if ( $('#tipVia').val() != "0" && $('#numVia').val() != ""){
                                $('#direccionModal').removeClass('mostrar');
                            }else{
                                alert("Debe llenar los campos tipo y número de vía");
                            }
                        }
        </script>
    </body>
</html>