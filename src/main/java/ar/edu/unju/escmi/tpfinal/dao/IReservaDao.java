package ar.edu.unju.escmi.tpfinal.dao;

import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;
import java.time.LocalDate;
import java.util.List;

public interface IReservaDao {
    void guardarReserva(Reserva reserva);
    void modificarReserva(Reserva reserva);
    void eliminarReservaLogicamente(Long id);
    Reserva buscarReservaPorId(Long id);
    List<Reserva> obtenerTodasLasReservas();
    List<Reserva> buscarReservasPorFecha(LocalDate fecha);
    List<Reserva> buscarReservasPorCliente(Long clienteId);
    boolean existeReservaEnFechaYHorario(Long salonId, LocalDate fecha, String horaInicio, String horaFin);
	void mostrarTodosLasReservas();
    
}
