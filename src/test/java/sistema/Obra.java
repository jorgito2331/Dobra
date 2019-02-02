/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema;

import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.DireccionDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.DireccionNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.DireccionImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ObraImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ParametroImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ObraNegocio;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ParametroNegocio;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.FuncionarioDTO;
import java.text.DecimalFormat;
import sistema.Reportes;
import org.joda.time.DateMidnight;
import org.joda.time.Days;
import java.util.Date;
import java.text.SimpleDateFormat;
import edu.polijic.garantizar.obraspublicas.garantizar.DTOs.ObraDTO;
import java.util.ArrayList;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.Implementacion.ObraImplementacion;
import edu.polijic.garantizar.obraspublicas.garantizar.Negocio.ObraNegocio;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Obra {

    private static ObraDTO objeto;
    private static ObraNegocio negocio;

    public Obra() throws SQLException, ClassNotFoundException {
        objeto = new ObraDTO();
        negocio = new ObraImplementacion();
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
    public void crearObra() throws SQLException, ClassNotFoundException {
        objeto.setNombre("JIC" + new SimpleDateFormat("yyyyMMdd").format(new Date()));
        objeto.setTipo("1");
        objeto.setValor("100000");
        objeto.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        objeto.setFechaFin(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //Parte de direccion
        DireccionDTO dTO1 = new DireccionDTO();
        dTO1.setTipoVia("Calle");
        dTO1.setNumVia(1);
        DireccionNegocio direccionNegocio = new DireccionImplementacion();
        direccionNegocio.crearDireccion(dTO1);
        dTO1.setId(direccionNegocio.obtenerId());
        ParametroNegocio negocio = new ParametroImplementacion();
        objeto.setDireccion(dTO1);
        objeto.setArgumentos(negocio.obtenerParametro("2").getNombre());
        objeto.setContratista("JIC" + new SimpleDateFormat("yyyyMMdd").format(new Date()));
        ObraNegocio negocioObra = new ObraImplementacion();
        String respuesta = negocioObra.crearObra(objeto);
        System.out.println(respuesta);
        assertTrue(respuesta == null);
    }

    @Test
    public void generarReporteObra() throws SQLException, ClassNotFoundException {
        ObraNegocio obraNegocio = new ObraImplementacion();
        ArrayList<ObraDTO> dTO = obraNegocio.obtenerObras("4");
        float tiempoDuracionPorc = 0; //guarda el porcentaje de tiempo
        float precioDuracionPorc = 0; //guarda el precio por cada porcentaje de tiempo
        float tiempoDesfXPorcDias = 0; //guarda la division entre la cantidad de dias desfasados y tiempoDuracionPorc
        int diasDesfasados = 0;
        double desfases = 0;
        int days = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        DateMidnight d1;
        DateMidnight d2;
        DateMidnight hoy = new DateMidnight(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        for (ObraDTO obra : dTO) {
            tiempoDuracionPorc = 0; //guarda el porcentaje de tiempo
            precioDuracionPorc = 0; //guarda el precio por cada porcentaje de tiempo
            tiempoDesfXPorcDias = 0; //guarda la division entre la cantidad de dias desfasados y tiempoDuracionPorc
            diasDesfasados = 0;
            desfases = 0;
            days = 0;
            d1 = new DateMidnight(obra.getFechaInicio());
            d2 = new DateMidnight(obra.getFechaFin());
            days = Days.daysBetween(d1, d2).getDays();
            obra.setTiempoDuracion(days + "");
            if (obra.getFinalizado() != null) {
                d1 = new DateMidnight(obra.getFechaFin());
                d2 = new DateMidnight(obra.getFinalizado());
                diasDesfasados = Days.daysBetween(d1, d2).getDays();
            } else {
                d2 = new DateMidnight(obra.getFechaFin());
                diasDesfasados = Days.daysBetween(d2, hoy).getDays();
            }
            String[] parametros = obra.getArgumentos().split(",");
            if (diasDesfasados > 0) {
                desfases = Float.valueOf(df.format(desfases + Float.valueOf(df.format(Float.parseFloat(obra.getValor()) * (Float.parseFloat(parametros[0]) / 100f)).replace(",", "."))).replace(",", "."));
                tiempoDuracionPorc = Float.valueOf(df.format(Float.parseFloat(obra.getTiempoDuracion()) * (Float.parseFloat(parametros[1]) / 100f)).replace(",", "."));
                precioDuracionPorc = Float.valueOf(df.format(Float.parseFloat(obra.getValor()) * (Float.parseFloat(parametros[2]) / 100f)).replace(",", "."));
                tiempoDesfXPorcDias = Float.valueOf(df.format(diasDesfasados / ((tiempoDuracionPorc == 0) ? 1f : tiempoDuracionPorc)).replace(",", "."));
                desfases = Float.valueOf(df.format(desfases + (Float.valueOf(df.format(tiempoDesfXPorcDias * precioDuracionPorc).replace(",", ".")))).replace(",", "."));
            }
            obra.setDesfaces(desfases + "");
            obra.setTiempoDuracion(days + "");
        }
        Reportes reportes = new Reportes(dTO, "Admin");
        reportes.start();
        
        System.out.println(reportes.getRespuesta());
        assertTrue(reportes.getRespuesta());
    }

}
