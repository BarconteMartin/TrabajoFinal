package ar.edu.unju.escmi.tpfinal.dao.imp;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.List;
import ar.edu.unju.escmi.tpfinal.config.EmfSingleton;
import ar.edu.unju.escmi.tpfinal.dao.ISalonDao;
import ar.edu.unju.escmi.tpfinal.entities.Salon;	
	
public class SalonDaoImp implements ISalonDao{
	
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();
	
	@Override
	public void mostrarTodosLosSalones() {
		Query query = manager.createQuery("SELECT e FROM Salon e",Salon.class);
		@SuppressWarnings("unchecked")
		List<Salon> salones = query.getResultList();
		for(Salon salones1 : salones) {
			salones1.mostrarDatos();
		}
	}

	@Override
	public Salon buscarSalonPorId(Long idSalon) {
			return manager.find(Salon.class, idSalon);
	}
	

	    @Override
	    public void guardarSalones() {
	        try {
	          
	            manager.getTransaction().begin();
	            
	           
	            Salon salon1 = new Salon(1L, "Cosmos", 60, 5000.0, false);
	            Salon salon2 = new Salon(2L, "Esmeralda", 20, 3000.0, false);
	            Salon salon3 = new Salon(3L, "Galaxy", 100, 8000.0, true);  //
	           
	            manager.merge(salon1);
	            manager.merge(salon2);
	            manager.merge(salon3);

	           
	            manager.getTransaction().commit();
	            
	        } catch (Exception e) {
				if (manager.getTransaction()!=null) {
					manager.getTransaction().rollback();
				}
				System.out.println("Ocurrio un error.");
			}		
		}
}
