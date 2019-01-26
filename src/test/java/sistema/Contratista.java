/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ContratistaDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ContratistaNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.DireccionNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ContratistaImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.DireccionImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ObraImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio;
import java.sql.SQLException;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class Contratista {

    private static ContratistaDTO objeto;
    private static ContratistaNegocio negocio;

    public Contratista() throws SQLException, ClassNotFoundException {
        objeto = new ContratistaDTO();
        negocio = new ContratistaImplementacion();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void crearContratista() throws SQLException, ClassNotFoundException {
        objeto.setNombre("Jorge" + new Date().toString());
        objeto.setCorreo("jorge_gaviria23181@elpoli.edu.co");
        objeto.setTipoID("1");
        objeto.setIdentificacion("1000409003");
        objeto.setTelefono("3136557557");
        //Parte de direccion
        DireccionDTO dTO1 = new DireccionDTO();
        dTO1.setTipoVia("Calle");
        dTO1.setNumVia(1);
        DireccionNegocio direccionNegocio = new DireccionImplementacion();
        direccionNegocio.crearDireccion(dTO1);
        dTO1.setId(direccionNegocio.obtenerId());
        ParametroNegocio negocio = new ParametroImplementacion();
        objeto.setDireccion(dTO1);
        ContratistaNegocio contratistaNegocio = new ContratistaImplementacion();
        String respuesta = contratistaNegocio.crearContratista(objeto);
        assertTrue(respuesta != null);
    }

}
