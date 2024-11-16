package ar.edu.unju.escmi.tpfinal.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "salon")
public class Salon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "salon_id")
	private Long id;
	
	@Column(name = "salon_nombre")
	private String nombre;
	
	@Column(name = "salon_capacidad")
	private int capacidad;
	
	@Column(name = "salon_precio")
	private double precio;

	@OneToMany(mappedBy = "salon", cascade = CascadeType.ALL )
	private List<Reserva> reservas;
	
	@Column(name = "salon_pileta")
	private boolean conPileta = true;
	
	
	public Salon(Long id, String nombre, int capacidad, double precio, boolean conPileta) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precio = precio;
        this.conPileta = conPileta;
        this.reservas = new ArrayList<>();
    }

	
	public Salon() {
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public boolean conPileta() {
		return conPileta;
	}
	public void conPileta(boolean conPileta) {
		this.conPileta = conPileta;
	}
	public List<Reserva> getReserva() {
		return reservas;
	}
	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas; 
	}

	public void mostrarDatos() {
		System.out.println("\nId del Salon: " + id);
		System.out.println("Salon: " + nombre);
		System.out.println("Capacidad: " + capacidad);
		System.out.println("Precio " + precio);
		System.out.println("Con pileta: " + (conPileta ? "Sí" : "No"));
		System.out.println("-------------------------------------------");
	}
}