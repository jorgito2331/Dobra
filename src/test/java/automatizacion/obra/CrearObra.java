/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automatizacion.obra;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author Usuario
 */
public class CrearObra {

    WebDriver driver;
    By irObras = By.id("obras");
    By btnAdicionar = By.id("cancelarBtn");

    By txtNombre = By.name("nombre");
    By txtValor = By.name("valor");
    By txtFechaInicio = By.name("inicio");
    By txtFechaFin = By.name("fin");
    By txtDireccion = By.name("completa");

    By btnGuardar = By.name("guar");

    public CrearObra(WebDriver driver, ObraDTO obraDTO) {
        this.driver = driver;
        clickIrObras();
        clickAdicionar();
        setTxtNombre(obraDTO.getNombre());
        setSlcTipoObra(obraDTO.getTipo());
        setTxtValor(obraDTO.getValor());
        setTxtFechaInicio(obraDTO.getFechaInicio());
        setTxtFechaFin(obraDTO.getFechaFin());
        new automatizacion.direccion.CrearDireccion(driver, obraDTO.getDireccion());
        clickTxtDireccion();
        setSlcContratista(obraDTO.getContratista());
        clickGuardar();
    }

    public void clickIrObras() {
        driver.findElement(irObras).click();
    }

    public void clickAdicionar() {
        driver.findElement(btnAdicionar).click();
    }

    public void setTxtNombre(String nombre) {
        driver.findElement(txtNombre).sendKeys(nombre);

    }

    public void setTxtValor(String valor) {
        driver.findElement(txtValor).sendKeys(valor);

    }

    public void setTxtFechaInicio(String fechaInicio) {
        driver.findElement(txtFechaInicio).sendKeys(fechaInicio);

    }

    public void setTxtFechaFin(String fechaFin) {
        driver.findElement(txtFechaFin).sendKeys(fechaFin);
    }

    public void setSlcTipoObra(String tipoObra) {
        new Select(driver.findElement(By.name("tipo"))).selectByValue(tipoObra);
    }

    public void setSlcContratista(String contratista) {
        new Select(driver.findElement(By.name("contratista"))).selectByValue(contratista);
    }

    public void clickGuardar() {
        driver.findElement(btnGuardar).click();
    }

    public void clickTxtDireccion() {
        driver.findElement(txtDireccion).click();
    }

    public boolean assertPrueba() {
        return driver.findElement(this.btnAdicionar).isDisplayed();
    }
}
