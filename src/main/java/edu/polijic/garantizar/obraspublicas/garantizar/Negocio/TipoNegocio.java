/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.TiposDTO;
import java.util.ArrayList;

/**
 *
 * @author Jorge-PC
 */
public interface TipoNegocio {
    public void crearTipo(TiposDTO tipo);
    public TiposDTO obtenerTipo(TiposDTO tipo);
    public ArrayList<TiposDTO> obtenertipos(TiposDTO tipo); 
    public void actualizartipo(TiposDTO tipo);
    public void eliminartipo(TiposDTO tipo);
    public void eliminartipos(TiposDTO tipo);
}
