/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.parametro;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.LogDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ParametroDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.LogImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.LogNegocio;
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

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Parametro", urlPatterns = {"/parametro"})
public class parametro extends HttpServlet {

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
        ParametroDTO dTO = new ParametroDTO();
        dTO.setId(request.getParameter("nuevoValor"));
        dTO.setArgumento(request.getParameter("id"));
        ParametroNegocio negocio = new ParametroImplementacion();
        negocio.actualizarParametro(dTO);
        LogDTO logDTO = new LogDTO();
        logDTO.setFuncionario(request.getSession().getAttribute("id").toString());
        logDTO.setTipoCambio("3");
        logDTO.setTipoObjeto("2");
        LogNegocio logNegocio = new LogImplementacion();
        logNegocio.crearLog(logDTO);
        response.sendRedirect("/Garantizar/parametro/manejar.jsp");
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
            Logger.getLogger(parametro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(parametro.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(parametro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(parametro.class.getName()).log(Level.SEVERE, null, ex);
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
