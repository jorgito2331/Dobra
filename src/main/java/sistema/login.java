/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.FuncionarioNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.FuncionarioImplementacion;
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
import javax.websocket.Session;

/**
 *
 * @author Jorge-PC
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
        String error = "";
        if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("/Garantizar/login.jsp");
        } else {
            FuncionarioNegocio funcionarioNegocio = new FuncionarioImplementacion();
            FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
            funcionarioDTO.setId(request.getParameter("indentificacion"));
            funcionarioDTO.setClave(request.getParameter("clave"));
            if (funcionarioDTO.getId() == null || funcionarioDTO.getClave() == null
                    || funcionarioDTO.getId().isEmpty() || funcionarioDTO.getClave().isEmpty()) {
                error = "Rellene todos los campos";
            } else {
                FuncionarioDTO validador = funcionarioNegocio.validar(funcionarioDTO);
                if (validador != null) {
                    HttpSession session = request.getSession();
                    validador.setClave("");
                    session.setAttribute("tipo", validador.getTipo());
                    session.setAttribute("id", validador.getId());
                    response.sendRedirect("/Garantizar/obra/manejar.jsp");
                } else {
                    error = "Usuario o contraseña incorrectos";
                }
            }
        }
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Ha courrido un error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<script>alert('" + error + "');window.location.replace('/Garantizar/login.jsp')</script>");
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
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
