/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import java.util.ArrayList;

/**
 *
 * @author Jorge-PC
 */
public interface ObraNegocio {
    public String crearObra(ObraDTO obra);
    public ObraDTO obtenerObra(ObraDTO obra);
    public ArrayList<ObraDTO> obtenerObras(String parametro); 
    public void actualizarObra(ObraDTO obra);
    public void eliminarObra(ObraDTO obra);
    public void eliminarObras(ObraDTO obra);
}
