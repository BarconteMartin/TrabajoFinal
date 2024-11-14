package ar.edu.unju.escmi.tpfinal.dao;

import ar.edu.unju.escmi.tpfinal.entities.Cliente;

public interface IClienteDao {
	public void guardarCliente(Cliente cliente);
	public void modificarCliente(Cliente cliente);
	public Cliente buscarClientePorId(Long idCliente);
	public void mostrarTodosLosClientes();
}
