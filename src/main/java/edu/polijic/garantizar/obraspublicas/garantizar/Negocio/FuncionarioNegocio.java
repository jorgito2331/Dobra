/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import java.util.ArrayList;

/**
 *
 * @author Jorge-PC
 */
public interface FuncionarioNegocio {
    public void actualizar(FuncionarioDTO funcionarioDTO);
    public void borrar(FuncionarioDTO funcionarioDTO);
    public ArrayList<FuncionarioDTO> obtenerTodos();
    public FuncionarioDTO validar(FuncionarioDTO funcionarioDTO);
    public void crear(FuncionarioDTO funcionarioDTO);
}
