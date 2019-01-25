
import automatizacion.sistema.Login;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class AutomatizacionInicializar {
    final static String LINK_GENERAL = "http://localhost:8080/Garantizar/login.jsp";
    final static String LINK_PANTALLA_PRINCIPAL = "http://localhost:8080/Garantizar/obra/manejar.jsp";
    public static WebDriver driver;
    
    @BeforeClass
    public static void setUpClass() {                
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();        
        driver.get(LINK_GENERAL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    
    @AfterClass
    public static void tearDownClass() {
        
    }
    
    @Before
    public void setUp() {
        driver.get(LINK_PANTALLA_PRINCIPAL);        
    }
    
    @After
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void iniciarSesion(){
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId("jorge.gaviria");
        funcionarioDTO.setClave("ABcd1234**");
        new Login(driver, funcionarioDTO);
    }
}
