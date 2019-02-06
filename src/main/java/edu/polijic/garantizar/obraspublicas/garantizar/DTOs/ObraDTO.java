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
public class ObraDTO {

    String nombre;
    String contratista;
    DireccionDTO direccion;
    String tipo;
    String valor;
    String fechaInicio;
    String fechaFin;
    String finalizado;
    String tiempoDuracion;
    String estado;
    String desfaces;
    String argumentos;
    String diasDesfase;
    String desfaseDinero;
    int diferenciaDias;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public DireccionDTO getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionDTO direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(String tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDesfaces() {
        return desfaces;
    }

    public void setDesfaces(String desfaces) {
        this.desfaces = desfaces;
    }
    
    public String getContratista() {
        return contratista;
    }

    public void setContratista(String contratista) {
        this.contratista = contratista;
    }

    public String getArgumentos() {
        return argumentos;
    }

    public void setArgumentos(String argumentos) {
        this.argumentos = argumentos;
    }

    public String getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(String finalizado) {
        this.finalizado = finalizado;
    }

    public String getDiasDesfase() {
        return diasDesfase;
    }

    public void setDiasDesfase(String diasDesfase) {
        this.diasDesfase = diasDesfase;
    }

    public String getDesfaseDinero() {
        return desfaseDinero;
    }

    public void setDesfaseDinero(String desfaseDinero) {
        this.desfaseDinero = desfaseDinero;
    }

    public int getDiferenciaDias() {
        return diferenciaDias;
    }

    public void setDiferenciaDias(int diferenciaDias) {
        this.diferenciaDias = diferenciaDias;
    }
    
}
