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
public class Buscar {
    WebDriver driver;
    By nombre = By.name("busq");
    By guar = By.name("guarManejar");

    public void setContratista(String nombre) {
        driver.findElement(this.nombre).sendKeys(nombre);
    }
    
    public void clickGuardar() {
        driver.findElement(guar).click();
    }  
}
