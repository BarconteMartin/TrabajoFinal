package ar.edu.unju.escmi.tpfinal.dao;

import ar.edu.unju.escmi.tpfinal.entities.Salon;

public interface ISalonDao {
	public Salon buscarSalonPorId(Long idSalon);
	public void mostrarTodosLosSalones();
	public void guardarSalones();
	
}
