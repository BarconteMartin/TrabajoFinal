package ar.edu.unju.escmi.tpfinal.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "servicios_adicionales")
public class ServicioAdicional {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Column(nullable = false)
	private String descripcion;
	
	@Column(nullable = false)
	private double precio;
	
	@Column(nullable = false)
	private boolean estado;
	
	@ManyToMany(mappedBy = "serviciosAdicionales")
	private List<Reserva> reservas;
	
	public ServicioAdicional() {
        this.estado = true; // Valor predeterminado
    }
	
	public ServicioAdicional(Long id, String descripcion, double precio, boolean estado) {
		this.id = id;
	    this.descripcion = descripcion;
	    this.precio = precio;
	    this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void mostrarDatos() {
	    System.out.println("Servicio ID: " + id);
	    System.out.println("Descripción: " + descripcion);
	    System.out.println("Precio: $" + precio);
	    System.out.println("Estado: " + (estado ? "Activo" : "Inactivo"));
	    System.out.println("-------------------------------------------");
	}

}