/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.obra;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.LogDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.DireccionNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.DireccionImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.LogImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ObraImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.LogNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ObraNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jorge-PC
 */
@WebServlet(name = "obra", urlPatterns = {"/obra"})
public class obra extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        if (request.getParameter("guardarValor") != null) {
            ObraDTO obraDTO = new ObraDTO();
            obraDTO.setNombre(request.getParameter("guardarValor"));
            obraDTO.setValor(request.getParameter("nuevoValor").replace("$", ""));
            ObraNegocio negocioObra = new ObraImplementacion();
            String respuesta = negocioObra.actualizarObra(obraDTO);
            if (respuesta != null) {

                devolver(respuesta, response);
            } else {
                LogDTO logDTO = new LogDTO();
                logDTO.setFuncionario(request.getSession().getAttribute("id").toString());
                logDTO.setTipoCambio("2");
                logDTO.setTipoObjeto("1");
                LogNegocio logNegocio = new LogImplementacion();
                logNegocio.crearLog(logDTO);
                devolver(respuesta, response);
            }
        } else if (request.getParameter("guardarNombre") != null) {
            ObraDTO obraDTO = new ObraDTO();
            obraDTO.setNombre(request.getParameter("guardarNombre"));
            obraDTO.setArgumentos(request.getParameter("nuevoValor"));
            ObraNegocio negocioObra = new ObraImplementacion();
            String respuesta = negocioObra.actualizarObra(obraDTO);
            if (respuesta != null) {
                devolver(respuesta, response);
            } else {
                LogDTO logDTO = new LogDTO();
                logDTO.setFuncionario(request.getSession().getAttribute("id").toString());
                logDTO.setTipoCambio("2");
                logDTO.setTipoObjeto("1");
                LogNegocio logNegocio = new LogImplementacion();
                logNegocio.crearLog(logDTO);
                devolver(respuesta, response);
            }
        } else if (request.getParameter("guarManejar") != null) {
            response.sendRedirect("obra/buscar.jsp?busq=" + request.getParameter("busq"));
        } else if (request.getParameter("finalizar") != null) {
            ObraDTO dTO = new ObraDTO();
            String[] datos = request.getParameter("finalizar").toUpperCase().split(";");
            dTO.setNombre(datos[0]);
            ObraNegocio negocioObra = new ObraImplementacion();
            negocioObra.eliminarObra(dTO);
            LogDTO logDTO = new LogDTO();
            logDTO.setFuncionario(request.getSession().getAttribute("id").toString());
            logDTO.setTipoCambio("3");
            logDTO.setTipoObjeto("1");
            LogNegocio logNegocio = new LogImplementacion();
            logNegocio.crearLog(logDTO);
            response.sendRedirect("obra/buscar.jsp?busq=" + datos[1]);
        } else if (request.getParameter("cancelar") != null) {
            response.sendRedirect("obra/manejar.jsp");
        } else {
            ObraDTO dTO = new ObraDTO();
            dTO.setNombre(request.getParameter("nombre").toUpperCase());
            dTO.setTipo(request.getParameter("tipo"));
            dTO.setValor(request.getParameter("valor"));
            dTO.setFechaInicio(request.getParameter("inicio"));
            dTO.setFechaFin(request.getParameter("fin"));
            //Parte de direccion
            DireccionDTO dTO1 = new DireccionDTO();
            dTO1.setCompleta(request.getParameter("completa"));
            DireccionNegocio direccionNegocio = new DireccionImplementacion();
            direccionNegocio.crearDireccion(dTO1);
            dTO1.setId(new DireccionImplementacion().obtenerId());
            ParametroNegocio negocio = new ParametroImplementacion();
            dTO.setDireccion(dTO1);
            dTO.setArgumentos(negocio.obtenerParametro("2").getNombre());
            dTO.setContratista(request.getParameter("contratista"));
            ObraNegocio negocioObra = new ObraImplementacion();
            String respuesta = negocioObra.crearObra(dTO);
            if (respuesta != null) {
                devolver(respuesta, response);
            } else {
                devolver(respuesta, response);
                LogDTO logDTO = new LogDTO();
                logDTO.setFuncionario(request.getSession().getAttribute("id").toString());
                logDTO.setTipoCambio("1");
                logDTO.setTipoObjeto("1");
                LogNegocio logNegocio = new LogImplementacion();
                logNegocio.crearLog(logDTO);
                response.sendRedirect("obra/manejar.jsp");
            }

        }
    }

    public void devolver(String respuesta, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");

            out.println("<html>");
            out.println("    <head>");
            if (respuesta != null) {
                out.println("        <title>Ha courrido un error</title>");
            } else {
                out.println("        <title>Guardado con exito</title>");
            }
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("        <style>");
            out.println("            @import url('https://fonts.googleapis.com/css?family=Roboto');");
            out.println("");
            out.println("            *{");
            out.println("                font-family: 'Roboto', sans-serif;");
            out.println("                font-size: medium;");
            out.println("            }");
            out.println("");
            out.println("            body{");
            out.println("                padding: 0;");
            out.println("                margin: 0;");
            out.println("                overflow: hidden;");
            if (respuesta == null) {
                out.println("                background: #bfdee0;");
            } else {
                out.println("                background: #606cd3;");
            }
            out.println("                width: 100vw;");
            out.println("                height: 100vh;");
            out.println("                display: flex;    ");
            out.println("                align-items: center;");
            out.println("                justify-content: center;");
            out.println("            }");
            out.println("");
            out.println("            #cover{");
            out.println("                width: 50%;");
            out.println("                height: 100%;");
            out.println("                display: flex;    ");
            out.println("                align-items: center;");
            out.println("                justify-content: center;");
            out.println("            }");
            out.println("");
            out.println("            #content{");
            out.println("                display: flex; ");
            out.println("                width: 50%;");
            out.println("                height: 100%;    ");
            out.println("                display: flex;    ");
            out.println("                flex-direction: column;");
            out.println("                justify-content: center;");
            out.println("                align-items: center;");
            out.println("                text-align: center;");
            out.println("            }");
            out.println("");
            out.println("            img{");
            out.println("                width: 90%;");
            out.println("                height: 90%;");
            out.println("            }");
            out.println("");
            out.println("            h1{");
            if (respuesta == null) {
                out.println("                color: black;");
            } else {
                out.println("                color: white;");
            }
            out.println("                font-size: xx-large;");
            out.println("            }");
            out.println("");
            out.println("            p{");
            out.println("                width: 100%;");
            out.println("                height: auto;  ");
            if (respuesta == null) {
                out.println("                color: black;");
            } else {
                out.println("                color: white;");
            }
            out.println("                margin: 5vh 10vw;");
            out.println("            }");
            out.println("");
            out.println("            a{");
            out.println("                height: 7vh;");
            out.println("                width: 15vw;");
            out.println("                margin: 0 1vw;");
            out.println("                color: white;");
            out.println("                display: flex;");
            out.println("                justify-content: center;");
            out.println("                align-items: center; ");
            out.println("                background: #4BB86B;");
            out.println("                text-decoration: none;");
            out.println("                transition: 0.3s;");
            out.println("                border-radius: 3px;");
            out.println("            }");
            out.println("");
            out.println("            a:hover{");
            out.println("                background: white;");
            out.println("                color: black;");
            out.println("                cursor: pointer;");
            out.println("            }");
            out.println("        </style>");
            out.println("");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <div id=\"cover\">");

            if (respuesta != null) {
                out.println("            <img src=\"./Imagenes/construccion.gif\" alt=\"\"/>");
            } else {
                out.println("            <img src=\"./Imagenes/casa.gif\" alt=\"\"/>");
            }
            out.println("        </div>");
            out.println("        <div id=\"content\">                ");
            if (respuesta != null) {
                out.println("            <h1>Ha ocurrido un error</h1>");
                out.println("            <p>" + respuesta + "</p>");
                out.println("            <a onclick='window.history.back();'>Volver</a>");
            } else {
                out.println("            <h1>Se ha guardado correctamente</h1>");
                out.println("            <a href=\"http://localhost:8888/Garantizar/obra/manejar.jsp\">Ir al inicio</a>");
            }
            out.println("        </div>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(obra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(obra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(obra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(obra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
