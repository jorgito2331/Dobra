/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obras;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Jorge-PC
 */
public class Crear {
    WebDriver driver;
    By nombre = By.name("nombre");
    By tipo = By.name("tipo");
    By valor = By.name("valor");
    By inicio = By.name("inicio");
    By fin = By.name("fin");
    By completa = By.name("completa");
    By contratista = By.name("contratista");
    By guar = By.name("guar");
    
    public Crear(WebDriver driver){
        this.driver = driver;
    }
    
    public void setNombre(String nombre) {
        driver.findElement(this.nombre).sendKeys(nombre);
    }

    public void setTipo(String tipo) {
        driver.findElement(this.tipo).sendKeys(tipo);
    }

    public void setValor(String valor) {
        driver.findElement(this.valor).sendKeys(valor);
    }

    public void setInicio(String inicio) {
        driver.findElement(this.inicio).sendKeys(inicio);
    }

    public void setFin(String fin) {
        driver.findElement(this.fin).sendKeys(fin);
    }

    public void setCompleta(String completa) {
        driver.findElement(this.completa).sendKeys(completa);
    }

    public void setContratista(String contratista) {
        driver.findElement(this.contratista).sendKeys(contratista);
    }
    
    public void clickGuardar() {
        driver.findElement(guar).click();
    }  
    
    
}
