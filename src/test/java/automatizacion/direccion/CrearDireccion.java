/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacion.direccion;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Usuario
 */
public class CrearDireccion {
    
    WebDriver driver;
    DireccionDTO direccionDTO;
    
    By txtTipoVia = By.name("TVia");
    By txtNumvia = By.id("numVia");
    By btnGuardar = By.id("guardar");
    
    public CrearDireccion(WebDriver driver, DireccionDTO direccionDTO) {
        this.driver = driver;
        this.direccionDTO = direccionDTO;
        
        setTxtTipoVia();
        setTxtNumVia();
        clickGuardar();
    }
    
    public void setTxtTipoVia() {
        new Select(driver.findElement(txtTipoVia)).selectByValue(direccionDTO.getTipoVia());
    }
    
    public void setTxtNumVia() {
        driver.findElement(txtNumvia).sendKeys(direccionDTO.getNumPri() + "");        
    }
    
    public void clickGuardar(){
        driver.findElement(btnGuardar).click();
    }
}
