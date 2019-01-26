/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacion.contratista;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ContratistaDTO contratistaDTO;
    //Icono del menu y boton adicionar
    By irContatista = By.id("contratistas");
    By btnAdicionar = By.id("crear");

    //Formulario crear contratista
    By txtNombre = By.name("nombre");
    By txtCorreo = By.name("correo");
    By txtTipoId = By.name("tipoId");
    Select slcTipoId;
    By txtIdentificacion = By.name("identificacion");
    By txtTelefono = By.name("telefono");
    By txtDireccion = By.name("completa");
    By btnGuardar = By.name("guar");
    
    public Crear(WebDriver driver, ContratistaDTO contratistaDTO) {
        this.contratistaDTO = contratistaDTO;
        this.driver = driver;
        
        clickIrContatista();
        clickBtnAdicionar();
        setTxtNombre();
        setTxtCorreo();
        setTxtTipoId();
        setTxtIdentificacion();
        setTxtTelefono();
        clickTxtDireccion();
        new automatizacion.direccion.Crear(driver, contratistaDTO.getDireccion());
        clickBtnGuardar();
        assertTest();
        

    }

    public void clickIrContatista() {
        driver.findElement(irContatista).click();
    }

    public void clickBtnAdicionar() {
        driver.findElement(btnAdicionar).click();
    }

    public void setTxtNombre() {
        driver.findElement(txtNombre).sendKeys(contratistaDTO.getNombre());
    }

    public void setTxtCorreo() {
        driver.findElement(txtCorreo).sendKeys(contratistaDTO.getCorreo());
    }

    public void setTxtTipoId() {
        slcTipoId = new Select(driver.findElement(txtTipoId));
        slcTipoId.selectByValue(contratistaDTO.getTipoID());
    }

    public void setTxtIdentificacion() {
        driver.findElement(txtIdentificacion).sendKeys(contratistaDTO.getIdentificacion());
    }

    public void setTxtTelefono() {
        driver.findElement(txtTelefono).sendKeys(contratistaDTO.getTelefono());
    }
    
    public void clickTxtDireccion(){
        driver.findElement(txtDireccion).click();
    }
    
    public void clickBtnGuardar(){
        driver.findElement(btnGuardar).click();
    }
    
    public void assertTest(){
        Assert.assertTrue(driver.findElement(btnAdicionar).isDisplayed());
    }
    
}
