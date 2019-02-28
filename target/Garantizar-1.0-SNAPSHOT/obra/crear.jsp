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
        <title>Crear obra</title>
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
            ArrayList<ContratistaDTO> contratistas;
            ArrayList<ParametroDTO> tipoObras;
            ContratistaNegocio contratista = new ContratistaImplementacion();
            contratistas = contratista.obtenerContratistas("1");
            ParametroNegocio negocio = new ParametroImplementacion();
            tipoObras = negocio.obtenerParametros("1");
        %>
        <div class="menu">
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
                <a href="manejar.jsp">Obra &#8250;</a>
                <a href="crear.jsp">Crear Obra &#8250;</a>
            </div>
            <form class="formulario" action="../obra" method="POST" autocomplete="off">
                <div class="campo">
                    <label>Nombre</label>
                    <input name="nombre" id="nombre" required="true">
                </div>
                <div class="campo">
                    <label>Valor</label>
                    <input type="number" name="valor" required="true">
                </div>
                <div class="campo">
                    <label>Dirección</label>
                    <input onclick="$('#direccionModal').addClass('mostrar')" name="completa" id="dirr" required="true" readonly>
                </div>
                <div class="campo">
                    <label>Tipo de obra</label>
                    <select name="tipo" id="tipoObra">
                        <option value="0">Seleccione</option>
                        <% for (ParametroDTO contratis : tipoObras) {
                        %>
                        <option value="<%= contratis.getId()%>"><%= contratis.getNombre()%></option>
                        <% }%>
                    </select>
                </div>
                <div class="campo">
                    <label>Fecha inicio</label>
                    <input type="date" id="fechaInicioTxt" onchange="document.getElementById('fechaFinTxt').setAttribute('min', document.getElementById('fechaInicioTxt').value);" name="inicio" required="true">
                </div>
                <div class="campo">
                    <label>Fecha fin</label>
                    <input type="date" name="fin" required="true" id="fechaFinTxt">
                </div>
                <div class="campo">
                    <label>Contratista</label>
                    <select name="contratista">
                        <option value="0">Seleccione</option>
                        <% for (ContratistaDTO contratis : contratistas) {
                        %>
                        <option value="<%= contratis.getNombre()%>"><%= contratis.getNombre()%></option>
                        <% }%>
                    </select>
                </div>
                <div class="control">
                    <a class="cancelar" id="cancelarBtn" href="manejar.jsp">Cancelar</a>
                    <button type="submit" class="guardar" id="GuardarForm" name="guar">Guardar</button>
                </div>
            </form>
        </div>
        <div class="modal" id="direccionModal">
            <div class="direccion">
                <div class="campoDir">
                    <label>Tipo de via</label>
                    <select name="TVia" id="tipVia" required="true">
                        <option value="">Seleccione</option>
                        <option value="Calle">Calle</option>
                        <option value="Carrera">Carrera</option>
                        <option value="Transversal">Transversal</option>
                        <option value="Diagonal">Diagonal</option>
                        <option value="Avenida">Avenida</option>
                    </select>
                </div>
                <div class="campoDir">
                    <label>Número de via</label>
                    <input type="number" max="999" id="numVia" value="" required="true">
                </div>
                <div class="campoDir">
                    <label>Sufijo de via</label>
                    <select id="sufVia">
                        <option value="">Seleccione</option>
                        <%
                            for (int i = 0; i <= 25; i++) {
                        %>
                        <option value="<%= (char) ('A' + i)%>"><%= (char) ('A' + i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="campoDir">
                    <label>Cardinalidad de via</label>
                    <select id="cardVia">
                        <option value="">Seleccione</option>
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
                <div class="campoDir">
                    <label>Primer número</label>
                    <input type="number" max="999" id="priNum" value="">
                </div>
                <div class="campoDir">
                    <label>Sufijo primer número</label>
                    <select id="sufPriNum">
                        <option value="">Seleccione</option>
                        <%
                            for (int i = 0; i <= 25; i++) {
                        %>
                        <option value="<%= (char) ('A' + i)%>"><%= (char) ('A' + i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="campoDir">
                    <label>Cardinalidad primer número</label>
                    <select id="cardPriNum">
                        <option value="">Seleccione</option>
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
                <div class="campoDir">
                    <label>Segundo número</label>
                    <input type="number" max="999" id="segNum" value="">
                </div>
                <div class="campoDir">
                    <label>Sufijo segundo número</label>
                    <select id="sufSegNum">
                        <option value="">Seleccione</option>
                        <%
                            for (int i = 0; i <= 25; i++) {
                        %>
                        <option value="<%= (char) ('A' + i)%>"><%= (char) ('A' + i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="controlDir">
                    <button class="cancelarDir" onclick="$('#direccionModal').removeClass('mostrar')">Cancelar</button>
                    <button class="guardarDir" onclick="guardar()">Guardar</button>
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
                            direccion = direccion + (($('#tipVia').val() == "") ? "0" : $('#tipVia').val()) + " ";
                            direccion = direccion + (($('#numVia').val() == "") ? "0" : $('#numVia').val()) + " ";
                            direccion = direccion + (($('#sufVia').val() == "") ? "0" : $('#sufVia').val()) + " ";
                            direccion = direccion + (($('#cardVia').val() == "") ? "0" : $('#cardVia').val()) + " ";
                            direccion = direccion + (($('#priNum').val() == "") ? "0" : $('#priNum').val()) + " ";
                            direccion = direccion + (($('#sufPriNum').val() == "") ? "0" : $('#sufPriNum').val()) + " ";
                            direccion = direccion + (($('#cardPriNum').val() == "") ? "0" : $('#cardPriNum').val()) + " ";
                            direccion = direccion + (($('#segNum').val() == "") ? "0" : $('#segNum').val()) + " ";
                            direccion = direccion + (($('#sufSegNum').val() == "") ? "0" : $('#sufSegNum').val()) + " ";
                            $('#GuardarForm').val(direccion);
                            var direccion = "";
                            direccion = direccion + $('#tipVia').val() + " ";
                            direccion = direccion + $('#numVia').val() + " ";
                            direccion = direccion + $('#sufVia').val() + " ";
                            direccion = direccion + $('#cardVia').val() + " ";
                            direccion = direccion + $('#priNum').val() + " ";
                            direccion = direccion + $('#sufPriNum').val() + " ";
                            direccion = direccion + $('#cardPriNum').val() + " ";
                            direccion = direccion + $('#segNum').val() + " ";
                            direccion = direccion + $('#sufSegNum').val() + " ";
                            $('#dirr').val(direccion);
                            if ( $('#tipVia').val() != "0" && $('#numVia').val() != ""){
                                $('#direccionModal').removeClass('mostrar');
                            }else{
                                alert("Debe llenar los campos tipo y número de vía");
                            }
                        }
        </script>
    </body>
</html>

