/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion;

import com.mysql.jdbc.Connection;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ContratistaNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Persistencia.ConexionPersistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge-PC
 */
public class ContratistaImplementacion implements ContratistaNegocio {

    ConexionPersistencia conexion;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;

    public ContratistaImplementacion() throws SQLException, ClassNotFoundException {
        conexion = new ConexionPersistencia();
        connection = conexion.obtener();
        resultSet = null;
        statement = null;
    }

    @Override
    public String crearContratista(ContratistaDTO contratista) {
        try {
            statement = connection.serverPrepareStatement("INSERT INTO `contratista` (`NOMBRE`, `CORREO`, `TIPO_ID`, `IDENTIFICACION`, `TELEFONO`, `DIRECCION`) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, contratista.getNombre());
            statement.setString(2, contratista.getCorreo());
            statement.setInt(3, Integer.parseInt(contratista.getTipoID()));
            statement.setString(4, contratista.getIdentificacion());
            statement.setString(5, contratista.getTelefono());
            statement.setInt(6, Integer.parseInt(contratista.getDireccion().getId()));
            statement.execute();            
        } catch (SQLException ex) {
             if (ex.getMessage().contains("Duplicate entry")) {
                return "El nombre del contratista ya está en uso";
            }else{
                return "Ha ocurrido un error";
            }
        }finally{
            conexion.cerrar();
        }
        return null;
    }

    @Override
    public ContratistaDTO obtenerContratista(ContratistaDTO contratista) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ContratistaDTO> obtenerContratistas(String criterio) {
        ArrayList<ContratistaDTO> contratistas = new ArrayList<>();
        ContratistaDTO cdto;
        String sql = "";
        try {
            sql = "SELECT cn.NOMBRE, cn.CORREO,ti.DESCRIPCION, cn.IDENTIFICACION, cn.TELEFONO, dir.DIR_COMPLETA FROM contratista cn INNER JOIN tipo_id ti ON cn.TIPO_ID = ti.ID INNER JOIN direccion dir ON cn.DIRECCION = dir.ID";           
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            do {
                cdto = new ContratistaDTO();
                cdto.setNombre(resultSet.getString(1));
                cdto.setCorreo(resultSet.getString(2));
                cdto.setTipoID(resultSet.getString(3));
                cdto.setIdentificacion(resultSet.getString(4));
                cdto.setTelefono(resultSet.getString(5));
                DireccionDTO dTO = new DireccionDTO();
                dTO.setCompleta(resultSet.getString(6));
                cdto.setDireccion(dTO);
                contratistas.add(cdto);
            } while (resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(ContratistaImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrar();
        }
        return contratistas;
    }
}
