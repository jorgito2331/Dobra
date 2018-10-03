/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.Negocio;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;

/**
 *
 * @author Jorge-PC
 */
public interface DireccionNegocio {
    public void crearDireccion(DireccionDTO direccion);
    public DireccionDTO obtenerDireccion(DireccionDTO direccion); 
    public void actualizarDireccion(DireccionDTO direccion);
    public String obtenerId();
}
