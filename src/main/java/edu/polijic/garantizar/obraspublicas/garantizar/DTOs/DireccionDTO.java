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
public class DireccionDTO {
    String id;
    String tipoVia;
    int numVia;
    String sufVia;
    String cardVia;
    int numPri;
    String sufPri;
    String cardPri;
    int numSeg;
    String sufSeg;
    String complemento;
    String completa;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public int getNumVia() {
        return numVia;
    }

    public void setNumVia(int numVia) {
        this.numVia = numVia;
    }

    public String getSufVia() {
        return sufVia;
    }

    public void setSufVia(String sufVia) {
        this.sufVia = sufVia;
    }

    public String getCardVia() {
        return cardVia;
    }

    public void setCardVia(String cardVia) {
        this.cardVia = cardVia;
    }

    public int getNumPri() {
        return numPri;
    }

    public void setNumPri(int numPri) {
        this.numPri = numPri;
    }

    public String getSufPri() {
        return sufPri;
    }

    public void setSufPri(String sufPri) {
        this.sufPri = sufPri;
    }

    public String getCardPri() {
        return cardPri;
    }

    public void setCardPri(String cardPri) {
        this.cardPri = cardPri;
    }

    public int getNumSeg() {
        return numSeg;
    }

    public void setNumSeg(int numSeg) {
        this.numSeg = numSeg;
    }

    public String getSufSeg() {
        return sufSeg;
    }

    public void setSufSeg(String sufSeg) {
        this.sufSeg = sufSeg;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }    

    public String getCompleta() {
        return completa;
    }

    public void setCompleta(String completa) {
        this.completa = completa;
    }
}
