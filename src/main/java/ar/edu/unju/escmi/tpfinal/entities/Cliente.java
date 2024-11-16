package ar.edu.unju.escmi.tpfinal.entities;

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
@Table(name = "clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cliente_id")
	private Long id;
	
	@Column(name = "cliente_nombre")
	private String nombre;
	
	@Column(name = "cliente_apellido")
	private String apellido;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Reserva> reservas;
	
	@Column(name = "cliente_domicilio")
	private String domicilio;
	
	@Column(name = "cliente_telefono")
	private int telefono;
	
	@Column(name = "cliente_dni", unique = true)
	private int dni;
	
	@Column(name = "cliente_estado")
	private boolean estado=true;
	
	
	public Cliente(String nombre, String apellido, String domicilio, int telefono, int dni, List<Reserva> reservas) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.dni = dni;
		this.telefono = telefono; 
		this.reservas = reservas;
	}
	
	public Cliente() {
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setDomicilio(int telefono) {
		this.telefono = telefono;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	public List<Reserva> getReserva() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas; 
	}

	public void mostrarClientes() {
		System.out.println("\nID del Cliente: " + id);
		System.out.println("Nombre del cliente: " + nombre);
		System.out.println("Apellido del cliente: " + apellido);
		System.out.println("DNI: " + dni);
		System.out.println("Domicilio: " + domicilio);
		System.out.println("Telefono: " + telefono);
		System.out.println("Estado: " + (estado ? "Activo" : "Inactivo"));
		System.out.println("-------------------------------------------");
	}
}