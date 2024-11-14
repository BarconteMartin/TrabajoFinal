package ar.edu.unju.escmi.tpfinal.tests;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

class ServicioAdicionalTest {
    private static EntityManagerFactory emf;
    private ServicioAdicional servicioCamara;
    private ServicioAdicional servicioCabina;
    private ServicioAdicional servicioFilmacion;
    
    @BeforeAll
    static void initAll() {
        emf = Persistence.createEntityManagerFactory("TPFinalPU");
    }

    @AfterAll
    static void closeAll() {
        emf.close();
    }

    @BeforeEach
    void setUp() {
        servicioCamara = new ServicioAdicional(1L, "Cámara 360", 20000.0, true);
        servicioCabina = new ServicioAdicional(2L, "Cabina de fotos", 15000.0, true);
        servicioFilmacion = new ServicioAdicional(3L, "Filmación", 25000.0, true);
    }

    @AfterEach
    void tearDown() {
        servicioCamara = null;
        servicioCabina = null;
        servicioFilmacion = null;
    }

    @Test
    void testCreacionServicio() {
        assertNotNull(servicioCamara, "El servicio no debe ser null");
        assertEquals("Cámara 360", servicioCamara.getDescripcion(),
                    "La descripción debe coincidir");
        assertEquals(20000.0, servicioCamara.getPrecio(),
                    "El precio debe coincidir");
    }

    @Test
    void testServiciosDistintos() {
        assertNotSame(servicioCamara, servicioCabina,
                     "Deben ser instancias diferentes");
        
        assertNotEquals(servicioCamara.getPrecio(), servicioCabina.getPrecio(),
                       "Los precios deben ser diferentes");
    }

    @Test
    void testEstadoServicio() {
        assertTrue(servicioCamara.isEstado(),
                  "El servicio debe estar activo inicialmente");
        
        servicioCamara.setEstado(false);
        assertFalse(servicioCamara.isEstado(),
                   "El servicio debe estar inactivo después de desactivarlo");
    }

    @Test
    void testComparacionPrecios() {
        double[] precios = {
            servicioCamara.getPrecio(),
            servicioCabina.getPrecio(),
            servicioFilmacion.getPrecio()
        };
        
        double[] preciosEsperados = {20000.0, 15000.0, 25000.0};
        assertArrayEquals(preciosEsperados, precios,
                         "Los precios deben coincidir con los valores esperados");
    }

    @Test
    void testModificacionServicio() {
        servicioCamara.setDescripcion("Cámara 360 Premium");
        servicioCamara.setPrecio(25000.0);
        
        assertEquals("Cámara 360 Premium", servicioCamara.getDescripcion(),
                    "La descripción actualizada debe coincidir");
        assertTrue(servicioCamara.getPrecio() > 20000.0,
                  "El precio actualizado debe ser mayor al original");
    }
}