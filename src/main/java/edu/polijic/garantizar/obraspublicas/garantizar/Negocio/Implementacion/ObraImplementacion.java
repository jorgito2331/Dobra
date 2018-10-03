/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion;

import com.mysql.jdbc.Connection;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.DireccionNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ObraNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio;
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
public class ObraImplementacion implements ObraNegocio {

    ConexionPersistencia conexion;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;

    public ObraImplementacion() throws SQLException, ClassNotFoundException {
        conexion = new ConexionPersistencia();
        connection = conexion.obtener();
        resultSet = null;
        statement = null;
    }

    @Override
    public void crearObra(ObraDTO obra) {
        try {
            statement = connection.prepareStatement("INSERT INTO `obra` (`ID`, `CONTRATISTA`, `TIPO`, `DIRECCION`, `FEC_INI`, `FEC_FIN`, `VALOR`, `DES_ARG`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, obra.getNombre());
            statement.setString(2, obra.getContratista());
            statement.setInt(3, Integer.parseInt(obra.getTipo()));
            statement.setInt(4, Integer.parseInt(obra.getDireccion().getId()));
            statement.setString(5, obra.getFechaInicio());
            statement.setString(6, obra.getFechaFin());
            statement.setString(7, obra.getValor());                        
            statement.setString(8, obra.getArgumentos());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ObraImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ObraDTO obtenerObra(ObraDTO obra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<ObraDTO> obtenerObras(String parametro) {
        ArrayList<ObraDTO> obras = new ArrayList<>();
        ObraDTO obra;
        String sql = "SELECT ob.ID, ob.CONTRATISTA, tob.DESCRIPCION, dir.DIR_COMPLETA, ob.FEC_INI, ob.FEC_FIN, ob.VALOR, ob.DES_ARG, ob.ESTADO FROM obra ob INNER JOIN direccion dir ON ob.DIRECCION = dir.ID INNER JOIN tipo_obra tob ON ob.DIRECCION = tob.ID WHERE ob.ESTADO = 1" ;
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            do{
               obra = new ObraDTO();
               obra.setNombre(resultSet.getString(1));
               obra.setContratista(resultSet.getString(2));
               obra.setTipo(resultSet.getString(3));
               DireccionDTO dTO = new DireccionDTO();
               dTO.setCompleta(resultSet.getString(4));
               obra.setDireccion(dTO);
               obra.setFechaInicio(resultSet.getString(5));
               obra.setFechaFin(resultSet.getString(6));
               obra.setValor(resultSet.getString(7));
               obra.setArgumentos(resultSet.getString(8));
               obra.setEstado(resultSet.getString(9));
               obras.add(obra);
            }while(resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(ObraImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obras;
    }

    @Override
    public void actualizarObra(ObraDTO obra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarObra(ObraDTO obra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarObras(ObraDTO obra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
