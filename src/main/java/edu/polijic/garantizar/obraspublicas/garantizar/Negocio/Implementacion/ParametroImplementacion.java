/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion;

import com.mysql.jdbc.Connection;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ParametroDTO;
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
public class ParametroImplementacion implements ParametroNegocio {

    ConexionPersistencia conexion;
    Connection connection;
    ResultSet resultSet;
    PreparedStatement statement;

    public ParametroImplementacion() throws SQLException, ClassNotFoundException {
        conexion = new ConexionPersistencia();
        connection = conexion.obtener();
        resultSet = null;
        statement = null;
    }

    @Override
    public void crearParametro(ParametroDTO parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ParametroDTO obtenerParametro(String parametro) {
        if (parametro.equals("2")) {
            String sql = "SELECT par.ARGUMENTO FROM parametro par WHERE par.ID IN (1, 2 , 3) GROUP BY par.ARGUMENTO";
            String datos = "";
            ParametroDTO par = new ParametroDTO();
            try {
                statement = connection.prepareStatement(sql);
                resultSet = statement.executeQuery();
                resultSet.first();
                do {
                    datos = datos + resultSet.getString(1) + ",";
                } while (resultSet.next());
                par.setNombre(datos);
            } catch (SQLException ex) {
                Logger.getLogger(ContratistaImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            }
            return par;
        }
        return null;
    }

    @Override
    public ArrayList<ParametroDTO> obtenerParametros(String parametro) {
        ArrayList<ParametroDTO> parametros = new ArrayList<>();
        ParametroDTO pdto;
        String sql = "";
        String datos = "";
        switch (parametro) {
            case "1":
                sql = "SELECT tob.ID, tob.DESCRIPCION FROM tipo_obra tob";
                break;
            case "2":
                sql = "SELECT tob.ID, tob.DESCRIPCION FROM tipo_id tob";
                break;
        }
        try {
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            resultSet.first();
            do {
                pdto = new ParametroDTO();
                pdto.setId(resultSet.getString(1));
                pdto.setNombre(resultSet.getString(2));
                parametros.add(pdto);
            } while (resultSet.next());
        } catch (SQLException ex) {
            Logger.getLogger(ContratistaImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parametros;
    }

    @Override
    public void actualizarParametro(ParametroDTO parametro) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
