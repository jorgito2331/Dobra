/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacion.obra;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Usuario
 */
public class BuscarObra {

    WebDriver driver;
    By irObras = By.id("obras");
    By btnBuscar = By.id("GuardarForm");
    By tblResultado = By.id("table_id");

    public BuscarObra(WebDriver driver) {
        this.driver = driver;

        clickIrObras();
        clickBuscar();
        assertPrueba();
    }

    public void clickIrObras() {
        driver.findElement(irObras).click();
    }

    public void clickBuscar() {
        driver.findElement(btnBuscar).click();
    }

    public boolean assertPrueba() {
        return driver.findElement(this.tblResultado).isDisplayed();
    }

}
