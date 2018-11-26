/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.polijic.garantizar.obraspublicas.garantizar.DTOs;

/**
 *
 * @author Jorge-PC
 */
public class ParametroDTO {
    String id;
    String nombre;
    String descripcion;
    String argumento;
    String reglas;
    String mensaje;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArgumento() {
        return argumento;
    }

    public void setArgumento(String argumento) {
        this.argumento = argumento;
    }

    public String getReglas() {
        return reglas;
    }

    public void setReglas(String reglas) {
        this.reglas = reglas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
}
