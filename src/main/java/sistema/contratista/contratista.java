/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.contratista;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.LogDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ContratistaNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.DireccionNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ContratistaImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.DireccionImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.LogImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.LogNegocio;
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

/**
 *
 * @author Jorge-PC
 */
@WebServlet(name = "contratista", urlPatterns = {"/contratista"})
public class contratista extends HttpServlet {

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
        if (request.getParameter("guarManejar") != null) {
            request.setAttribute("busqueda", request.getParameter("busq"));
            if (request.getParameter("nombre") != null) {
                request.setAttribute("nombre", request.getParameter("nombre"));
            }
            response.sendRedirect("contratista/buscar.jsp");
        }
        if (request.getParameter("cancelar") != null) {
            response.sendRedirect("contratista/manejar.jsp");
        } else {
            ContratistaDTO cdto = new ContratistaDTO();
            cdto.setNombre(request.getParameter("nombre").toUpperCase());
            cdto.setCorreo(request.getParameter("correo"));
            cdto.setTipoID(request.getParameter("tipoId"));
            cdto.setIdentificacion(request.getParameter("identificacion"));
            cdto.setTelefono(request.getParameter("telefono"));
            DireccionDTO dTO1 = new DireccionDTO();
            dTO1.setCompleta(request.getParameter("completa"));
            DireccionNegocio direccionNegocio = new DireccionImplementacion();
            direccionNegocio.crearDireccion(dTO1);
            dTO1.setId(direccionNegocio.obtenerId());
            cdto.setDireccion(dTO1);
            ContratistaNegocio contratistaNegocio = new ContratistaImplementacion();
            String respuesta = contratistaNegocio.crearContratista(cdto);
            if (respuesta != null) {
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Ha courrido un error</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<script>alert('" + respuesta + "');window.history.back();</script>");
                    out.println("</body>");
                    out.println("</html>");
                }
            } else {
                LogDTO logDTO = new LogDTO();
                logDTO.setFuncionario(request.getSession().getAttribute("id").toString());
                logDTO.setTipoCambio("1");
                logDTO.setTipoObjeto("2");
                LogNegocio logNegocio = new LogImplementacion();
                logNegocio.crearLog(logDTO);
                response.sendRedirect("contratista/manejar.jsp");
            }
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
            Logger.getLogger(contratista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(contratista.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(contratista.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(contratista.class.getName()).log(Level.SEVERE, null, ex);
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
