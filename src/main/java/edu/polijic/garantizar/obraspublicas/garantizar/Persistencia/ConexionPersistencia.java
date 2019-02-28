/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Persistencia;

import com.mysql.jdbc.Connection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge-PC
 */
public class ConexionPersistencia {

    Connection conexion;

    public Connection obtener() throws SQLException, ClassNotFoundException {
        conexion = null;
        if (conexion == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/dobra", "root", "12345678");
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return conexion;
    }

    public void cerrar() {
        try {
            if (null != conexion) {
                conexion.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
