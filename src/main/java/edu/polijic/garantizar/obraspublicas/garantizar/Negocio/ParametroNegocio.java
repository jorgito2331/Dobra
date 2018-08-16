/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ParametroDTO;
import java.util.ArrayList;

/**
 *
 * @author Jorge-PC
 */
public interface ParametroNegocio {
    public void crearParamentro(ParametroDTO parametro);
    public ParametroDTO obtenerParamentro(ParametroDTO parametro);
    public ArrayList<ParametroDTO> obtenerParamentros(ParametroDTO parametro); 
    public void actualizarParamentro(ParametroDTO parametro);
}
