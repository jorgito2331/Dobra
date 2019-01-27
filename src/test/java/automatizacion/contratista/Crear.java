/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacion.contratista;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Usuario
 */
public class Crear {
    WebDriver driver;
    
    //Generales
    By irAContratistas = By.id("contratistas");
    By btnAdicionar = By.id("crear");
    
    //Campos
    By txtNombre = By.name("nombre");
    By txtCorreo = By.name("correo");
    By txtIdentificacion = By.name("identificacion");
    By txtTelefono = By.name("telefono");
    By txtDireccion = By.name("completa");
    
    //Selects
    Select slcTipoId = new Select(driver.findElement(By.name("tipoId")));
    
    //Guardar
    By btnGuardar = By.name("guar");
    
    public Crear(WebDriver driver) {
        this.driver = driver;
    }

    public void clickIrAContratistas() {
        driver.findElement(irAContratistas).click();
    }

    public void clickBtnAdicionar() {
        driver.findElement(btnAdicionar).click();
    }

    public void setTxtNombre(String nombre) {
        driver.findElement(txtNombre).sendKeys(nombre);
    }

    public void setTxtCorreo(String correo) {
        driver.findElement(txtCorreo).sendKeys(correo);
    }

    public void setTxtIdentificacion(String id) {
        driver.findElement(txtIdentificacion).sendKeys(id);
    }

    public void setTxtTelefono(String Telefono) {
        driver.findElement(txtTelefono).sendKeys(Telefono);
    }

    public void clickTxtDireccion() {
        driver.findElement(txtDireccion).click();
    }

    public void setSlcTipoId(String tipoId) {
        slcTipoId.selectByValue(tipoId);
    }
    
    public void clickBtnGuardar() {
        driver.findElement(btnGuardar).click();
    }
    
    public void assertTest(){
        Assert.assertTrue(driver.findElement(btnAdicionar).isDisplayed());
    }
    
}
