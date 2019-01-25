/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacion.sistema;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Usuario
 */
public class Login {

    By txtIdentificacion = By.name("indentificacion");
    By txtPassword = By.name("clave");
    By btnIniciar = By.id("iniciar");
    By btnAssert = By.id("obras");
    WebDriver driver;

    public Login(WebDriver driver, FuncionarioDTO funcionarioDTO) {
        this.driver = driver;
        this.setTxtIdentificacion(funcionarioDTO.getId());
        this.setTxtPassword(funcionarioDTO.getClave());
        this.clickLogin();
    }

    public void setTxtIdentificacion(String txtIdentificacion) {
        driver.findElement(this.txtIdentificacion).sendKeys(txtIdentificacion);
    }

    public void setTxtPassword(String clave) {
        driver.findElement(this.txtPassword).sendKeys(clave);
    }

    public void clickBtnIniciar() {
        driver.findElement(btnIniciar).click();
    }

    public void clickLogin() {
        driver.findElement(btnIniciar).click();
    }
    
    public void assertPrueba(){
        Assert.assertTrue(driver.findElement(btnAssert).isDisplayed());
    }
}
