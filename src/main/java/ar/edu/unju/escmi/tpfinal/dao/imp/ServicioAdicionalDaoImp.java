package ar.edu.unju.escmi.tpfinal.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.tpfinal.config.EmfSingleton;
import ar.edu.unju.escmi.tpfinal.dao.IServicioAdicionalDao;
import ar.edu.unju.escmi.tpfinal.entities.ServicioAdicional;

public class ServicioAdicionalDaoImp implements IServicioAdicionalDao{
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void guardarServicio(ServicioAdicional servicio) {
		try {
			manager.getTransaction().begin();
			manager.persist(servicio);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void modificarServicio(ServicioAdicional servicio) {
		try {
			manager.getTransaction().begin();
			manager.merge(servicio);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public void eliminarServicioLogicamente(Long id) {
		try {
			manager.getTransaction().begin();
			ServicioAdicional servicio = manager.find(ServicioAdicional.class, id);
			if (servicio != null) {
				servicio.setEstado(false);
				manager.merge(servicio);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			throw e;
		}
	}

	@Override
	public ServicioAdicional buscarPorId(Long id) {
		return manager.find(ServicioAdicional.class, id);
	}

	@Override
	public List<ServicioAdicional> obtenerTodosLosServicios() {
		TypedQuery<ServicioAdicional> query = manager.createQuery("SELECT s FROM ServicioAdicional s WHERE r.estado = true", ServicioAdicional.class);
		return query.getResultList();	
	}

	@Override
	public List<ServicioAdicional> buscarServiciosPorEstado(boolean estado) {
		TypedQuery<ServicioAdicional> query = manager.createQuery("SELECT s FROM ServicioAdicional s WHERE r.estado = true", ServicioAdicional.class);
		query.setParameter("estado", estado);
		return query.getResultList();	
	}



}
