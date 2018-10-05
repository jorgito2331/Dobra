/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.obra;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.DireccionNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.DireccionImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ObraImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ObraNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio;
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
        if (request.getParameter("guarManejar") != null) {
            request.setAttribute("busqueda", request.getParameter("busq"));
            if (request.getParameter("nombre") != null) {
                request.setAttribute("nombre", request.getParameter("nombre"));
            }
            response.sendRedirect("obra/buscar.jsp");
        } else {
            String[] direccion = request.getParameter("guar").split(" ");
            ObraDTO dTO = new ObraDTO();
            dTO.setNombre(request.getParameter("nombre").toUpperCase());
            dTO.setTipo(request.getParameter("tipo"));
            dTO.setValor(request.getParameter("valor"));
            dTO.setFechaInicio(request.getParameter("inicio"));
            dTO.setFechaFin(request.getParameter("fin"));
            //Parte de direccion
            DireccionDTO dTO1 = new DireccionDTO();
            dTO1.setTipoVia(direccion[0]);
            dTO1.setNumVia(Integer.parseInt(direccion[1]));
            dTO1.setSufVia(direccion[2]);
            dTO1.setCardVia(direccion[3]);
            dTO1.setNumPri(Integer.parseInt(direccion[4]));
            dTO1.setSufPri(direccion[5]);
            dTO1.setCardPri(direccion[6]);
            dTO1.setNumSeg(Integer.parseInt(direccion[7]));
            dTO1.setSufSeg(direccion[8]);
            dTO1.setCompleta(request.getParameter("completa"));
            DireccionNegocio direccionNegocio = new DireccionImplementacion();
            direccionNegocio.crearDireccion(dTO1);
            dTO1.setId(direccionNegocio.obtenerId());
            ParametroNegocio negocio = new ParametroImplementacion();
            dTO.setDireccion(dTO1);
            dTO.setArgumentos(negocio.obtenerParametro("2").getNombre());
            dTO.setContratista(request.getParameter("contratista"));
            ObraNegocio negocioObra = new ObraImplementacion();
            negocioObra.crearObra(dTO);
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
