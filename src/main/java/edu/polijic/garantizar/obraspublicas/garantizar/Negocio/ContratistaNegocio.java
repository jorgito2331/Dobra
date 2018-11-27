/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO;
import java.util.ArrayList;

/**
 *
 * @author Jorge-PC
 */
public interface ContratistaNegocio {
    public boolean crearContratista(ContratistaDTO contratista);
    public ContratistaDTO obtenerContratista(ContratistaDTO contratista);
    public ArrayList<ContratistaDTO> obtenerContratistas(String criterio); 
    public void actualizarContratista(ContratistaDTO contratista);
    public void eliminarContratista(ContratistaDTO contratista);
    public void eliminarContratistas(ContratistaDTO contratista);
}
