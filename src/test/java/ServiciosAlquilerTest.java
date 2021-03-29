
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.*;

public class ServiciosAlquilerTest {

    @Inject
    private SqlSession sqlSession;

    ServiciosAlquiler serviciosAlquiler;

    public ServiciosAlquilerTest() {
        serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
    }

    /* @Before
    public void setUp() {
    } */

    /* @Test
    public void emptyDB() {
        for(int i = 0; i < 100; i += 10) {
            boolean r = false;
            try {
                Cliente cliente = serviciosAlquiler.consultarCliente(2159518);
            } catch(ExcepcionServiciosAlquiler e) {
                r = true;
            } catch(IndexOutOfBoundsException e) {
                r = true;
            }
            // Validate no Client was found;
            Assert.assertTrue(r);
        };
    } */

    @Test
    public void noDeberiaConsultarElCostoDeUnItemDesconocido() throws ExcepcionServiciosAlquiler{
        try {
            assertEquals(101010101, serviciosAlquiler.consultarCostoAlquiler(91919191, 818181));
            fail("No entro a la excepcion");
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void deberiaDeConsultarElCostoDeUnItemAgregado() throws ExcepcionServiciosAlquiler{
        try {
            Item item = new Item(new TipoItem(1, "prueba" ),6,
                    "prueba", "prueba Pa", new SimpleDateFormat("yyyy/MM/dd").parse("2020/10/4"),
                    100,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            assertEquals(100*5, serviciosAlquiler.consultarCostoAlquiler(6, 5));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void deberiaConsultarTarifaxDia() throws ParseException, ExcepcionServiciosAlquiler {
        try {
            /* Cliente cliente = new Cliente("Nombre",111,"telefono","direccion","email",false,null); */
            Item item = new Item(new TipoItem(1, "prueba" ),6,
                    "prueba", "prueba Pa", new SimpleDateFormat("yyyy/MM/dd").parse("2020/10/4"),
                    100,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            assertEquals(100, serviciosAlquiler.valorMultaRetrasoxDia(6));
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    @Test
    public void deberiaConsultarCliente(){
        try {
            ArrayList<ItemRentado> itemRentados = new ArrayList<ItemRentado>();
            Cliente cliente = new Cliente("Nombre",11,"telefono","direccion","email",false,itemRentados);
            serviciosAlquiler.registrarCliente(cliente);
            assertEquals("Nombre", serviciosAlquiler.consultarCliente(11).getNombre());
        } catch (Exception e) {
            fail("Entro a la excepcion");
        }
    }

    @Test
    public void deberiaDeConsultarItem(){
        try {
            Item item = new Item(new TipoItem(1, "prueba" ),1,
                    "prueba", "prueba Pa", new SimpleDateFormat("yyyy/MM/dd").parse("2020/10/4"),
                    100,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            assertEquals(1, serviciosAlquiler.consultarItem(1).getId());
        } catch (Exception e) {
            fail("Entro a la excepcion");
        }
    }

    @Test
    public void deberiaActualizarTarifaItem(){
        try {
            Item item = new Item(new TipoItem(1, "prueba" ),2,
                    "prueba", "prueba Pa", new SimpleDateFormat("yyyy/MM/dd").parse("2020/10/4"),
                    100,"prueba","prueba");
            serviciosAlquiler.registrarItem(item);
            serviciosAlquiler.actualizarTarifaItem(2, 150);
            assertEquals(150, serviciosAlquiler.consultarItem(2).getTarifaxDia());
        } catch (Exception e) {
            fail("Entro a la excepcion");
        }
    }

    @Test
    public void noDeberiaRegistrarAlquilerDeClienteQueNoExiste(){
        try {
            Item item = new Item(new TipoItem(8, "prueba" ),10,
                    "prueba", "prueba", new SimpleDateFormat("yyyy/MM/dd").parse("2020/10/04"),
                    100,"prueba","prueba");
            serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(LocalDate.parse("2020-10-04")) , serviciosAlquiler.consultarCliente(98989898).getDocumento() , item , 4 );
            assertTrue(false);
        } catch (Exception e) {
            assertTrue(true);
        }
    }

    @Test
    public void deberiRegistrarItemAlCliente() throws ExcepcionServiciosAlquiler {
        try {
            Item item = new Item(new TipoItem(8, "prueba" ),1010,
                    "prueba", "prueba", new SimpleDateFormat("yyyy/MM/dd").parse("2020/10/04"),
                    100,"prueba","prueba");
            Cliente cliente = new Cliente("prueba", 1989, "prueba", "prueba","prueba");
            serviciosAlquiler.registrarCliente(cliente);
            serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(LocalDate.parse("2020-10-04")) , serviciosAlquiler.consultarCliente(1989).getDocumento() , item , 4 );
            assertEquals(Date.valueOf(LocalDate.parse("2020-10-04")), serviciosAlquiler.consultarCliente(1989).getRentados().get(0).getFechainiciorenta());
        } catch (Exception e) {
            fail("Error manco");
        }
    }

    @Test
    public void deberiaConsultarMulta() throws ExcepcionServiciosAlquiler {
        try {
            ArrayList<ItemRentado> itemRentados = new ArrayList<ItemRentado>();
            Cliente cliente = new Cliente("Prueba", 1276, "Prueba", "Prueba", "Prueba", false, itemRentados);
            serviciosAlquiler.registrarCliente(cliente);
            Item it = new Item(new TipoItem(1, "prueba"), 6513,
                    "prueba", "prueba", new SimpleDateFormat("yyyy/MM/dd").parse("2019/09/28"),
                    65, "prueba", "prueba");
            serviciosAlquiler.registrarItem(it);
            LocalDate fechainicio = LocalDate.parse("2021-10-28");
            LocalDate fechafin = LocalDate.parse("2021-10-29");
            LocalDate fechaDevolucion = LocalDate.parse("2021-11-01");
            itemRentados.add(new ItemRentado(1, it, Date.valueOf(fechainicio), Date.valueOf(fechafin)));
            serviciosAlquiler.registrarAlquilerCliente(Date.valueOf(fechainicio), 1276, it, 1);
            serviciosAlquiler.consultarMultaAlquiler(6513, Date.valueOf(fechaDevolucion));
        } catch (Exception e) {
            fail(e.toString());
        }
    }
}