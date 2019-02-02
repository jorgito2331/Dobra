
import automatizacion.contratista.CrearContratista;
import automatizacion.obra.BuscarObra;
import automatizacion.obra.CrearObra;
import automatizacion.sistema.Login;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
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
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AutomatizacionInicializar {

    private final static String LINK_GENERAL = "http://localhost:8888/Garantizar/login.jsp";
    private static WebDriver driver;
    private static ObraDTO obraDTO;
    private static ContratistaDTO contratistaDTO;
    private static FuncionarioDTO funcionarioDTO;

    @BeforeClass
    public static void setUpClass() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(LINK_GENERAL);
        try {
            setearDatos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDownClass() {
        driver.close();
    }

    @Before
    public void setUp() {
        driver.get(LINK_GENERAL);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void test01Login() {
        try {
            Login login = new Login(driver, funcionarioDTO);
            Assert.assertTrue(login.assertPrueba());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test02CrearContratista() {
        try {
            CrearContratista crear = new CrearContratista(driver, contratistaDTO);
            Assert.assertTrue(crear.assertPrueba());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test03CrearObra() {
        try {
            CrearObra crearObra = new CrearObra(driver, obraDTO);            
            Assert.assertTrue(crearObra.assertPrueba());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void test04BuscarObra() {
        try{
            BuscarObra buscarObra = new BuscarObra(driver);
            Assert.assertTrue(buscarObra.assertPrueba());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void setearDatos() throws SQLException, ClassNotFoundException {
        
        //Parte Funcionario
        funcionarioDTO = new FuncionarioDTO();
        funcionarioDTO.setId("admin");
        funcionarioDTO.setClave("jorge123");
        
        //Parte de direccion
        DireccionDTO direccionDTO = new DireccionDTO();
        direccionDTO.setTipoVia("Calle");
        direccionDTO.setNumVia(1);
        
        //Parte contrastista
        contratistaDTO = new ContratistaDTO();
        contratistaDTO.setNombre("Jorge" + new Date().toString());
        contratistaDTO.setCorreo("jorge_gaviria23181@elpoli.edu.co");
        contratistaDTO.setTipoID("1");
        contratistaDTO.setIdentificacion("1000409003");
        contratistaDTO.setTelefono("3136557557");
        contratistaDTO.setDireccion(direccionDTO);
        
        //Parte obra
        obraDTO = new ObraDTO();
        obraDTO.setNombre("prueba" + new Date().toString());
        obraDTO.setTipo("1");
        obraDTO.setValor("100000");
        obraDTO.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        obraDTO.setFechaFin(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        ParametroNegocio negocio = new ParametroImplementacion();
        obraDTO.setDireccion(direccionDTO);
        obraDTO.setArgumentos(negocio.obtenerParametro("2").getNombre());
        obraDTO.setContratista(contratistaDTO.getNombre());
    }
}
