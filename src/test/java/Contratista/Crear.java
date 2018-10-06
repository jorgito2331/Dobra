/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Contratista;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Jorge-PC
 */
public class Crear {

    WebDriver driver;
    By nombre = By.name("nombre");
    By email = By.name("email");
    By tipoIdenti = By.name("tipoIdenti");
    By identificacion = By.name("identificacion");
    By telefono = By.name("telefono");
    By completa = By.name("completa");
    By guar = By.name("guar");

    public Crear(WebDriver driver) {
        String pathToChromeDriver = "drivers/chromedriver";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        this.driver = new ChromeDriver();
    }

    public void setNombre(String nombre) {
        driver.findElement(this.nombre).sendKeys(nombre);
    }

    public void setEmail(String email) {
        driver.findElement(this.email).sendKeys(email);
    }

    public void setTipoIdenti(String tipoIdenti) {
        driver.findElement(this.tipoIdenti).sendKeys(tipoIdenti);
    }

    public void setIdentificacion(String identificacion) {
        driver.findElement(this.identificacion).sendKeys(identificacion);
    }

    public void setTelefono(String telefono) {
        driver.findElement(this.telefono).sendKeys(telefono);
    }

    public void setCompleta(String completa) {
        driver.findElement(this.completa).sendKeys(completa);
    }

    public void clickGuardar() {
        driver.findElement(guar).click();
    }

}
