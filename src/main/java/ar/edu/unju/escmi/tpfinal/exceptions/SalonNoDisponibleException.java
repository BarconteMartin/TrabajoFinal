package ar.edu.unju.escmi.tpfinal.exceptions;

public class SalonNoDisponibleException extends Exception{
	private static final long serialVersionUID = 1L;
    public SalonNoDisponibleException(String mensaje) {
        super(mensaje);
    }

}
