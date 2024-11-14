package ar.edu.unju.escmi.tpfinal.exceptions;

public class ClienteNoExisteException extends Exception {
	private static final long serialVersionUID = 1L;
    public ClienteNoExisteException(String mensaje) {
        super(mensaje);
    }

}
