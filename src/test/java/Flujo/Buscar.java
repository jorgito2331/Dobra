/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Flujo;

import Obras.Crear;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Jorge-PC
 */
public class Buscar {
    
    private static WebDriver driver;
    Crear obra;
    Contratista.Crear contratista;
    Obras.Buscar obras;
    
    public Buscar() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        String pathToChromeDriver = "drivers/chromedriver.exe";

        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);

        driver = new ChromeDriver();     
        
        driver.navigate().to("http://localhost:8080/Garantizar/obra/manejar.jsp");
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() { 
    }
    
    @After
    public void tearDown() {      
        driver.quit();      
    }

    @Test
    public void CrearContratistaValido() {        
        contratista = new Contratista.Crear(driver);
        contratista.setNombre("INEM");
        contratista.setTipoIdenti("2");
        contratista.setIdentificacion("1000409003");
        contratista.setTelefono("5711445");
        contratista.setEmail("jorge_gaviria23181@elpoli.edu.co");
        contratista.setCompleta("CLL 88 CRA 52 A 45");
        obra.clickGuardar();
    }
    
    @Test
    public void CrarObraDesfasada() {
        obra = new Crear(driver);
        obra.setNombre("HOTEL");
        obra.setTipo("2");
        obra.setInicio("12/09/2018");
        obra.setFin("12/09/2019");
        obra.setValor("10000");
        obra.setCompleta("CLL 88 CRA 52 A 45");
        obra.clickGuardar();
    }
    
    @Test
    public void BuscarObraDesfasada() {
        obras = new Obras.Buscar();
        obras.setContratista("INEM");
        obras.clickGuardar();
    }
}
