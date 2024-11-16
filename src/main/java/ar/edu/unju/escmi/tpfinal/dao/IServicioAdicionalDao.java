package ar.edu.unju.escmi.tpfinal.dao;

import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;
import java.util.List;

public interface IServicioAdicionalDao {
    void guardarServicio();
    void modificarServicio(ServicioAdicional servicio);
    void eliminarServicioLogicamente(Long id);
    ServicioAdicional buscarPorId(Long id);
    List<ServicioAdicional> obtenerTodosLosServicios();
    List<ServicioAdicional> buscarServiciosPorEstado(boolean estado);
    void mostrarTodosLosServiciosAdicionales();
}