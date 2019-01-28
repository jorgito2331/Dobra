package automatizacion.contratista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Usuario
 */
public class CrearContratista {
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
    
    //Guardar
    By btnGuardar = By.name("guar");
    
    public CrearContratista(WebDriver driver) {
        this.driver = driver;
    }

    public void clickIrAContratistas() {
        driver.findElement(this.irAContratistas).click();
    }

    public void clickBtnAdicionar() {
        driver.findElement(this.btnAdicionar).click();
    }

    public void setTxtNombre(String nombre) {
        driver.findElement(this.txtNombre).sendKeys(nombre);
    }

    public void setTxtCorreo(String correo) {
        driver.findElement(this.txtCorreo).sendKeys(correo);
    }

    public void setTxtIdentificacion(String id) {
        driver.findElement(this.txtIdentificacion).sendKeys(id);
    }

    public void setTxtTelefono(String Telefono) {
        driver.findElement(this.txtTelefono).sendKeys(Telefono);
    }

    public void clickTxtDireccion() {
        driver.findElement(this.txtDireccion).click();
    }

    public void setSlcTipoId(String tipoId) {
        new Select(driver.findElement(By.name("tipoId"))).selectByValue(tipoId);
    }
    
    public void clickBtnGuardar() {
        driver.findElement(this.btnGuardar).click();
    }
    
    public void assertPrueba(){
        Assert.assertTrue(driver.findElement(this.btnAdicionar).isDisplayed());
    }
    
}

