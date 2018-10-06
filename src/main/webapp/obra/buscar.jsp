<%-- 
    Document   : buscar
    Created on : 11-ago-2018, 22:48:42
    Author     : Jorge-PC
--%>

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
        <title>Buscar Obra</title>        
        <link href="../Estilos/Menu.css" rel="stylesheet">
        <link href="../Estilos/Bread.css" rel="stylesheet">
        <link href="../Estilos/General.css" rel="stylesheet">
        <link href="../Estilos/Tabla.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">
    </head>
    <body>
        <%
            ObraNegocio obraNegocio = new ObraImplementacion();
            ArrayList<ObraDTO> dTO = obraNegocio.obtenerObras(request.getParameter("busq"));
            for (ObraDTO obra : dTO) {
                int diasDesfasados;
                double porcentaje = 0;
                int porcentajeDias;
                double desfases = 0;
                DateMidnight d1 = new DateMidnight(obra.getFechaInicio());
                DateMidnight d2 = new DateMidnight(obra.getFechaFin());
                DateMidnight hoy = new DateMidnight(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                int days = Days.daysBetween(d1, d2).getDays();
                obra.setTiempoDuracion(days + "");
                String[] parametros = obra.getArgumentos().split(",");
                diasDesfasados = Days.daysBetween(d2, hoy).getDays();
                if (diasDesfasados > 0) {
                    porcentaje = (Integer.parseInt(parametros[0]) / 100d);
                    porcentaje = Integer.parseInt(obra.getValor()) * porcentaje;
                    porcentajeDias = (int) (Math.round(days * (Integer.parseInt(parametros[1]) / 100d)));
                    try {
                        desfases = Math.round((((Double.parseDouble(obra.getValor()) * (Integer.parseInt(parametros[2]) / 100d)) * (diasDesfasados / porcentajeDias)) + porcentaje) * 100d) / 100d;
                    } catch (Exception e) {
                        System.out.println(obra.getFechaFin());
                        continue;
                    }
                }
                obra.setDesfaces(desfases + "");
                obra.setTiempoDuracion(days + "");
            }
            Reportes reportes = new Reportes(dTO);
            reportes.start();
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
                <a href="manejar.jsp">Obra &#8250;</a>
            </div>
            <div class="tabla">
                <table id="table_id" class="display">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Contratista</th>
                            <th>Tipo</th>
                            <th>Dirección</th>
                            <th>Inicio</th>
                            <th>Fin</th>
                            <th>Duración</th>
                            <th>Valor</th>
                            <th>Desfase</th>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (dTO != null) {
                                for (ObraDTO cdto : dTO) {%>
                        <tr>
                            <td><%= cdto.getNombre()%></td>
                            <td><%= cdto.getContratista()%></td>
                            <td><%= cdto.getTipo()%></td>
                            <td><%= cdto.getDireccion().getCompleta()%></td>
                            <td><%= cdto.getFechaInicio()%></td>
                            <td><%= cdto.getFechaFin()%></td>
                            <td><%= cdto.getTiempoDuracion()%></td>
                            <td><%= cdto.getValor()%></td>
                            <td><%= cdto.getDesfaces()%></td>
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