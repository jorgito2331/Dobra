/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion;

import com.mysql.jdbc.Connection;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.LogDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.LogNegocio;
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
public class LogImplementacion implements LogNegocio{

    ConexionPersistencia conexion;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;

    public LogImplementacion() throws SQLException, ClassNotFoundException {
        conexion = new ConexionPersistencia();
        connection = conexion.obtener();
        resultSet = null;
        statement = null;
    }

    @Override
    public void crearLog(LogDTO log) {
        try {
            statement = connection.prepareStatement("INSERT INTO `log` (`FUNCIONARIO`, `FECHA`, `TIPO_OBJETO`, `TIPO_CAMBIO`) VALUES (?, CURRENT_TIMESTAMP, ?, ?)");
            statement.setString(1, log.getFuncionario());
            statement.setInt(2, Integer.parseInt(log.getTipoObjeto()));
            statement.setInt(3, Integer.parseInt(log.getTipoCambio()));
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ObraImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            conexion.cerrar();
        }    
    }
}
