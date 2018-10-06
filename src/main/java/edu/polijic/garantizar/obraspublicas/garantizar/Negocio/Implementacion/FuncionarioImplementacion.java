/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.FuncionarioNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Persistencia.ConexionPersistencia;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge-PC
 */
public class FuncionarioImplementacion implements FuncionarioNegocio{
    
    ConexionPersistencia persistencia;
    Connection connection;
    PreparedStatement statement;
    ResultSet rs;
            
    public FuncionarioImplementacion() throws SQLException, ClassNotFoundException{
        persistencia = new ConexionPersistencia();
        connection = persistencia.obtener();
        statement = null;
    }
    
    @Override
    public void actualizar(FuncionarioDTO funcionarioDTO) {
        String sql = "UPDATE `funcionario` SET `CLAVE`= ?,`PRI_NOMBRE`= ?,`SEG_NOMBRE`= ?,`PRI_APELLIDO`= ?,`SEG_APELLIDO`= ? WHERE `ID` = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, funcionarioDTO.getClave());
            statement.setString(2, funcionarioDTO.getPriNombre());
            statement.setString(3, funcionarioDTO.getSegNombre());
            statement.setString(4, funcionarioDTO.getPriApellido());
            statement.setString(5, funcionarioDTO.getSegApellido());            
            statement.setString(1, funcionarioDTO.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void borrar(FuncionarioDTO funcionarioDTO) {
        String sql = "UPDATE `funcionario` SET `ESTADO`= ? WHERE `ID` = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, 0);
            statement.setString(2, funcionarioDTO.getId());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public FuncionarioDTO validar(FuncionarioDTO funcionarioDTO) {
        String sql = "SELECT * FROM `funcionario` WHERE `ID` = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, funcionarioDTO.getId());
            rs = statement.executeQuery();
            rs.first();
            if (rs.getString(2).equals(getMD5(funcionarioDTO.getClave()))) {
                FuncionarioDTO dTO = new FuncionarioDTO();
                dTO.setId(rs.getString(1));
                dTO.setClave(rs.getString(2));
                dTO.setPriNombre(rs.getString(3));
                dTO.setSegNombre(rs.getString(4));
                dTO.setPriApellido(rs.getString(5));
                dTO.setSegApellido(rs.getString(6));
                dTO.setTipo(rs.getString(7));
                dTO.setEstado(rs.getInt(8));
                String[] permisos = rs.getString(9).split(",");
                boolean[] permiso = new boolean[permisos.length];
                for (int i = 0; i < permisos.length; i++) {
                    if (permisos[i].equals("0")) {
                        permiso[i] = false;
                    }else{
                        permiso[i] = true;
                    }
                }
                dTO.setPermisos(permiso);
                return dTO;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void crear(FuncionarioDTO funcionarioDTO) {
        String sql = "INSERT INTO `funcionario` (`ID`, `CLAVE`, `PRI_NOMBRE`, `SEG_NOMBRE`, `PRI_APELLIDO`, `SEG_APELLIDO`) VALUES (?, MD5(?), ?, ?, ?, ?)";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, funcionarioDTO.getId());
            statement.setString(2, funcionarioDTO.getClave());
            statement.setString(3, funcionarioDTO.getPriNombre());
            statement.setString(4, funcionarioDTO.getSegNombre());
            statement.setString(5, funcionarioDTO.getPriApellido());
            statement.setString(6, funcionarioDTO.getSegApellido());
            statement.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImplementacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
