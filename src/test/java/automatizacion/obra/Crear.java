/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacion.obra;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Usuario
 */
public class Crear {
    WebDriver driver;
    By irObras = By.id("obras");

    public Crear(WebDriver driver, ObraDTO obra) {
        this.driver = driver;
    }
    
    public void clickLogin() {
        driver.findElement(irObras).click();
    }
}
