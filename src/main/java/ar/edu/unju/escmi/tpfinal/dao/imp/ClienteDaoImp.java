package ar.edu.unju.escmi.tpfinal.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;
import ar.edu.unju.escmi.tpfinal.config.EmfSingleton;
import ar.edu.unju.escmi.tpfinal.dao.IClienteDao;
import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;


public class ClienteDaoImp implements IClienteDao{
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	
	@Override
	public void guardarCliente(Cliente cliente) {
		try {
			manager.getTransaction().begin();
			manager.merge(cliente);
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction()!=null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar el cliente.");
		}
	}

	
	@Override
	public void modificarCliente(Cliente cliente) {
		try {
			manager.getTransaction().begin();
			manager.merge(cliente);
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction()!=null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo modificar los datos del cliente.");
		}		
	}

	@Override
	public void mostrarTodosLosClientes() {
		Query query = manager.createQuery("SELECT e FROM Cliente e",Cliente.class);
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = query.getResultList();
		for(Cliente cli : clientes) {
			cli.mostrarClientes();
			
		}
	}

	@Override
	public Cliente buscarClientePorId(Long idCliente) {
		
			return manager.find(Cliente.class, idCliente);
	}

	@Override
	public Cliente buscarClientePorDni(int dni) {
	    String jpql = "SELECT c FROM Cliente c WHERE c.dni = :dni";
	    try {
	        return manager.createQuery(jpql, Cliente.class)
	                      .setParameter("dni", dni)
	                      .getSingleResult();
	    } catch (NoResultException e) {
	        return null; // Si no hay resultados, retorna null.
	    }
	}


	
	@Override
    public List<Cliente> obtenerTodasLosClientes() {
        TypedQuery<Cliente> query = manager.createQuery("SELECT r FROM Cliente r WHERE r.estado = true", Cliente.class);
        return query.getResultList();
    }
	
}
