/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion;

import com.mysql.jdbc.Connection;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.DireccionNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Persistencia.ConexionPersistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge-PC
 */
public class DireccionImplementacion implements DireccionNegocio {

    ConexionPersistencia conexion;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;

    public DireccionImplementacion() throws SQLException, ClassNotFoundException {
        conexion = new ConexionPersistencia();
        connection = conexion.obtener();
        resultSet = null;
        statement = null;
    }

    @Override
    public void crearDireccion(DireccionDTO direccion) {
        try {
            statement = connection.prepareStatement("INSERT INTO `direccion` (`DIR_COMPLETA`) VALUES (?)");
            statement.setString(1, direccion.getCompleta());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DireccionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public DireccionDTO obtenerDireccion(DireccionDTO direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarDireccion(DireccionDTO direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String obtenerId() {
        String codigo = "";
        try {
            statement = connection.prepareStatement("SELECT MAX(dr.ID) FROM direccion dr");
            resultSet = statement.executeQuery();
            resultSet.first();
            codigo = resultSet.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(DireccionImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return codigo;
    }

}
