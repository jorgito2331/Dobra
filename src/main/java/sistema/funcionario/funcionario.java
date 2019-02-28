/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.funcionario;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.LogDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.FuncionarioNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.FuncionarioImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.LogImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.LogNegocio;
import java.io.IOException;
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
@WebServlet(name = "funcionario", urlPatterns = {"/funcionario"})
public class funcionario extends HttpServlet {

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
            response.sendRedirect("funcionario/buscar.jsp");
        } else {
            FuncionarioDTO cdto = new FuncionarioDTO();
            cdto.setId(request.getParameter("nombre"));
            cdto.setClave(request.getParameter("clave"));
            cdto.setPriNombre(request.getParameter("priNom"));
            cdto.setSegNombre(request.getParameter("segNom"));
            cdto.setPriApellido(request.getParameter("priApe"));
            cdto.setSegApellido(request.getParameter("segApe"));
            FuncionarioNegocio funcionarioNegocio = new FuncionarioImplementacion();
            funcionarioNegocio.crear(cdto);
            LogDTO logDTO = new LogDTO();
            logDTO.setFuncionario(request.getSession().getAttribute("id").toString());
            logDTO.setTipoCambio("4");
            logDTO.setTipoObjeto("1");
            LogNegocio logNegocio = new LogImplementacion();
            logNegocio.crearLog(logDTO);
            response.sendRedirect("funcionario/manejar.jsp");
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
            Logger.getLogger(funcionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(funcionario.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(funcionario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(funcionario.class.getName()).log(Level.SEVERE, null, ex);
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
