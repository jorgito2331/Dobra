<%-- 
    Document   : buscar
    Created on : 11-ago-2018, 22:48:42
    Author     : Jorge-PC
--%>

<%@page import="edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO"%>
<%@page import="java.text.DecimalFormat"%>
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
            float tiempoDuracionPorc = 0; //guarda el porcentaje de tiempo
            float precioDuracionPorc = 0; //guarda el precio por cada porcentaje de tiempo
            float tiempoDesfXPorcDias = 0; //guarda la division entre la cantidad de dias desfasados y tiempoDuracionPorc
            int diasDesfasados = 0;
            double desfases = 0;
            int days = 0;
            DecimalFormat df = new DecimalFormat("#.##");
            DateMidnight d1;
            DateMidnight d2;
            String rol = session.getAttribute("tipo").toString();
            DateMidnight hoy = new DateMidnight(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            for (ObraDTO obra : dTO) {
                tiempoDuracionPorc = 0; //guarda el porcentaje de tiempo
                precioDuracionPorc = 0; //guarda el precio por cada porcentaje de tiempo
                tiempoDesfXPorcDias = 0; //guarda la division entre la cantidad de dias desfasados y tiempoDuracionPorc
                diasDesfasados = 0;
                desfases = 0;
                days = 0;
                d1 = new DateMidnight(obra.getFechaInicio());
                d2 = new DateMidnight(obra.getFechaFin());
                days = Days.daysBetween(d1, d2).getDays();
                obra.setTiempoDuracion(days + "");
                if (obra.getFinalizado() != null) {
                    d1 = new DateMidnight(obra.getFechaFin());
                    d2 = new DateMidnight(obra.getFinalizado());
                    diasDesfasados = Days.daysBetween(d1, d2).getDays();
                } else {
                    d2 = new DateMidnight(obra.getFechaFin());
                    diasDesfasados = Days.daysBetween(d2, hoy).getDays();
                }
                String[] parametros = obra.getArgumentos().split(",");
                if (diasDesfasados > 0) {
                    desfases = Float.valueOf(df.format(desfases + Float.valueOf(df.format(Float.parseFloat(obra.getValor()) * (Float.parseFloat(parametros[0]) / 100f)).replace(",", "."))).replace(",", "."));
                    tiempoDuracionPorc = Float.valueOf(df.format(Float.parseFloat(obra.getTiempoDuracion()) * (Float.parseFloat(parametros[1]) / 100f)).replace(",", "."));
                    precioDuracionPorc = Float.valueOf(df.format(Float.parseFloat(obra.getValor()) * (Float.parseFloat(parametros[2]) / 100f)).replace(",", "."));
                    tiempoDesfXPorcDias = Float.valueOf(df.format(diasDesfasados / ((tiempoDuracionPorc == 0) ? 1f : tiempoDuracionPorc)).replace(",", "."));
                    desfases = Float.valueOf(df.format(desfases + (Float.valueOf(df.format(tiempoDesfXPorcDias * precioDuracionPorc).replace(",", ".")))).replace(",", "."));
                }
                obra.setDesfaces(desfases + "");
                obra.setTiempoDuracion(days + "");
            }
            Reportes reportes = new Reportes(dTO);
            reportes.start();
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
            <% if (rol.equals("ADMIN")) {%>
            <div class="menuItem" onclick="window.location.replace('../parametro/manejar.jsp')">
                <div class="image" id="ajustes"></div>
                <label>Ajustes</label>
            </div>
            <% }%>
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
                                <%if (session.getAttribute("tipo").equals("ADMIN")) {%>
                            <th>Acciones</th>
                                <%}%>
                        </tr>
                    </thead>
                    <tbody>

                        <%
                            if (dTO != null) {
                                for (ObraDTO cdto : dTO) {%>
                        <tr>
                            <td>
                                <form class="campoForm" action="../obra" method="POST">
                                    <input name="nuevoValor" value="<%= cdto.getNombre()%>"
                                           onkeyup="guardar<%= cdto.getNombre()%>.style.display = 'flex'"
                                           required="true">
                                    <button id="guardar<%= cdto.getNombre()%>" name="guardarNombre" value="<%= cdto.getNombre()%>" style="display : none">Guardar</button>
                                </form>
                            </td>
                            <td><%= cdto.getContratista()%></td>
                            <td><%= cdto.getTipo()%></td>
                            <td><%= cdto.getDireccion().getCompleta()%></td>
                            <td><%= cdto.getFechaInicio()%></td>
                            <td><%= cdto.getFechaFin()%></td>
                            <td><%= cdto.getTiempoDuracion()%></td>
                            <td>
                                <form class="campoForm" action="../obra" method="POST">
                                    <input name="nuevoValor" value="<%= cdto.getValor()%>"
                                           onkeyup="guardar<%= cdto.getNombre()%>Valor.style.display = 'flex'"
                                           required="true">
                                    <button id="guardar<%= cdto.getNombre()%>Valor" name="guardarValor" value="<%= cdto.getNombre()%>" style="display : none">Guardar</button>
                                </form>
                            </td>
                            <td><%= cdto.getDesfaces()%></td>
                            <%if (rol.equals("ADMIN") && cdto.getFinalizado() == null) {%>
                            <td>
                                <form action="../obra" method="POST">
                                    <button type="submit" name="finalizar" value="<%= cdto.getNombre()%>;<%= request.getParameter("busq")%>">Finalizar</button>
                                </form>
                            </td>
                            <%}%>
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