package ar.edu.unju.escmi.tpfinal.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;
import ar.edu.unju.escmi.tpfinal.entities.Salon;
import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class ReservaTest {
    private static EntityManagerFactory emf;
    private Reserva reserva;
    private Cliente cliente;
    private Salon salon;
    private List<ServicioAdicional> servicios;

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
        cliente = new Cliente(1L, "12345678", "Juan", "Perez", "Av. Test 123", "3884123456", true);
        salon = new Salon(1L, "Cosmos", 60, false, 100000.0);
        servicios = new ArrayList<>();
        servicios.add(new ServicioAdicional(1L, "Cámara 360", 20000.0, true));
        
        reserva = new Reserva();
        reserva.setId(1L);
        reserva.setCliente(cliente);
        reserva.setSalon(salon);
        reserva.setFecha(LocalDate.now());
        reserva.setHoraInicio(LocalTime.of(14, 0));
        reserva.setHoraFin(LocalTime.of(18, 0));
        reserva.setServiciosAdicionales(servicios);
        reserva.setPagoAdelantado(50000.0);
        reserva.setMontoPagado(50000.0);
        reserva.setCancelado(false);
        reserva.setEstado(true);
    }

    @AfterEach
    void tearDown() {
        reserva = null;
        cliente = null;
        salon = null;
        servicios.clear();
    }

    @Test
    void testCalcularCostoHorarioExtendido() {
        // Probar cálculo de horas extras (2 horas adicionales)
        reserva.setHoraFin(LocalTime.of(20, 0));
        double costoExtra = reserva.calcularCostoHorarioExtendido();
        assertEquals(20000.0, costoExtra, "El costo de 2 horas extras debe ser $20000");
    }

    @Test
    void testCalcularMontoTotal() {
        // Monto total = precio salón + servicios adicionales
        double montoTotal = reserva.calcularMontoTotal();
        assertEquals(120000.0, montoTotal, "El monto total debe ser $120000");
    }

    @Test
    void testCalcularPagoPendiente() {
        double pagoPendiente = reserva.calcularPagoPendiente();
        assertEquals(70000.0, pagoPendiente, "El pago pendiente debe ser $70000");
    }

    @Test
    void testReservaValida() {
        assertNotNull(reserva.getCliente(), "El cliente no debe ser null");
        assertNotNull(reserva.getSalon(), "El salón no debe ser null");
        assertTrue(reserva.getServiciosAdicionales().size() > 0, "Debe tener servicios adicionales");
    }

    @Test
    void testEstadoReserva() {
        // Probar estado de cancelación al pagar el total
        reserva.setMontoPagado(120000.0);
        assertTrue(reserva.getCancelado(), "La reserva debe estar cancelada al pagar el total");
        
        // Probar estado de cancelación con pago parcial
        reserva.setMontoPagado(50000.0);
        assertFalse(reserva.getCancelado(), "La reserva no debe estar cancelada con pago parcial");
    }

    @Test
    void testHorarioValido() {
        // Verificar que el horario esté dentro del rango permitido (10:00 - 23:00)
        LocalTime horaInicio = reserva.getHoraInicio();
        LocalTime horaFin = reserva.getHoraFin();
        
        assertTrue(horaInicio.isAfter(LocalTime.of(9, 59)) && 
                  horaInicio.isBefore(LocalTime.of(23, 1)),
                  "La hora de inicio debe estar entre 10:00 y 23:00");
        
        assertTrue(horaFin.isAfter(horaInicio), 
                  "La hora de fin debe ser posterior a la hora de inicio");
    }

    @Test
    void testServiciosAdicionales() {
        // Verificar que los servicios adicionales sean los mismos objetos
        ServicioAdicional servicio = servicios.get(0);
        assertSame(servicio, reserva.getServiciosAdicionales().get(0),
                  "Debe ser la misma instancia del servicio adicional");
    }
}