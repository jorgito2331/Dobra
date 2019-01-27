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
            dTO1.setId(direccionNegocio.obtenerId());
            ParametroNegocio negocio = new ParametroImplementacion();
            dTO.setDireccion(dTO1);
            dTO.setArgumentos(negocio.obtenerParametro("2").getNombre());
            dTO.setContratista(request.getParameter("contratista"));
            ObraNegocio negocioObra = new ObraImplementacion();
            String respuesta = negocioObra.crearObra(dTO);
            if (respuesta != null) {
                devolver(respuesta, response);
            } else {
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
            out.println("<head>");
            out.println("<title>Ha courrido un error</title>");
            out.println("</head>");
            out.println("<body>");
            if (respuesta == null) {
                out.println("<script>window.history.back();</script>");
            } else {
                out.println("<script>alert('" + respuesta + "');window.history.back();</script>");
            }
            out.println("</body>");
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
