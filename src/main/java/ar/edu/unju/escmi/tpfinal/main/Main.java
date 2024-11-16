package ar.edu.unju.escmi.tpfinal.main;

import java.util.List;
import java.util.Scanner;

import ar.edu.unju.escmi.tpfinal.dao.imp.ClienteDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.ReservaDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.SalonDaoImp;
import ar.edu.unju.escmi.tpfinal.dao.imp.ServicioAdicionalDaoImp;
import ar.edu.unju.escmi.tpfinal.entities.Cliente;
import ar.edu.unju.escmi.tpfinal.entities.Reserva;

public class Main {
    private static ClienteDaoImp ClienteDao = new ClienteDaoImp();
    private static SalonDaoImp SalonDao = new SalonDaoImp();
    private static ReservaDaoImp ReservaDao = new ReservaDaoImp();
    private static ServicioAdicionalDaoImp ServiciosAdicionalesDao = new ServicioAdicionalDaoImp();
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option;
        
        do {
            System.out.println("======= MENU =======");
            System.out.println("1. Alta Cliente");
            System.out.println("2. Modificar Cliente");
            System.out.println("3. Consultar Clientes");
            System.out.println("4. Consultar Salones");
            System.out.println("5. Consultar Reserva por ID");
            System.out.println("6. Consultar Todas las Reservas");
            System.out.println("7. Consultar Servicios Adicionales");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = sc.nextInt();
            sc.nextLine();  // Limpiar el buffer

            switch (option) {
                case 1:
                    altaCliente(sc);
                    break;
                case 2:
                    modificarCliente(sc);
                    break;
                case 3:
                    consultaClientes(sc);
                    break;
                case 4:
                    consultaSalones(sc);
                    break;
                case 5:
                    System.out.print("Ingrese el ID de la reserva: ");
                    Long numId = sc.nextLong();
                    consultarReserva(numId);
                    break;
                case 6:
                    consultarTodasLasReservas(sc);
                    break;
                case 7:
                    consultaServiciosAdicionales(sc);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción inválida, intente nuevamente.");
            }
            System.out.println();
        } while (option != 0);
        
        sc.close();
    }

    public static void altaCliente(Scanner sc) {
        System.out.println("========== REGISTRO DE CLIENTES ==========");
        System.out.println("Ingrese Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese Apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Ingrese Domicilio: ");
        String domicilio = sc.nextLine();
        
       
        boolean datoInvalido;
        int dni = 0;

        do {
            datoInvalido = false;
            try {
                System.out.print("Ingrese DNI: ");
                dni = Integer.parseInt(sc.nextLine());

                if (ClienteDao.buscarClientePorDni(dni) != null) {
                    System.out.println("El DNI ya fue registrado. Intente con otro.");
                    datoInvalido = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Dato no válido. Por favor, ingrese un número entero para el DNI.");
                datoInvalido = true;
            }
        } while (datoInvalido);

       

        
        
        
        int telefono = 0;
        do {
            try {
                datoInvalido = false;
                System.out.print("Ingrese Telefono: ");
                telefono = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nDato no valido, vuelva a ingresar el Telefono");
                datoInvalido = true;
                sc.nextLine();
            }
        } while (datoInvalido);    
        
        List<Reserva> reservas = null;
        Cliente cliente = new Cliente(nombre, apellido, domicilio, telefono, dni, reservas);
        ClienteDao.guardarCliente(cliente);
        System.out.println("\nCliente registrado exitosamente!");
    }
    
    public static void modificarCliente(Scanner sc) {
        ClienteDao.mostrarTodosLosClientes();
        
        Cliente cliente = null;
        Long idCliente = 0L;
        boolean datoInvalido = true;
        do {
            try {
                System.out.print("\nIngrese el ID del cliente a modificar: ");
                idCliente = sc.nextLong();
                
                cliente = ClienteDao.buscarClientePorId(idCliente);
                
                if (cliente == null) {
                    System.out.println("Cliente no encontrado. Intente nuevamente");
                } else datoInvalido = false;
            } catch (Exception e) {
                System.out.println("\nDato no valido, ingrese nuevamente el ID");
                sc.nextLine();
            }
        } while (datoInvalido);

        sc.nextLine();
        
        System.out.print("Ingrese el nuevo nombre: ");
        cliente.setNombre(sc.nextLine());

        System.out.print("Ingrese el nuevo apellido: ");
        cliente.setApellido(sc.nextLine());

        System.out.print("Ingrese el nuevo domicilio: ");
        cliente.setDomicilio(sc.nextLine());

        do {
            try {
                datoInvalido = false;
                System.out.print("Ingrese el nuevo DNI: ");
                cliente.setDni(sc.nextInt());
            } catch (Exception e) {
                System.out.println("\nDato no valido, vuelva a ingresar el nuevo DNI");
                datoInvalido = true;
                sc.nextLine();
            }
        } while (datoInvalido);

        int telefono = 0;
        do {
            try {
                datoInvalido = false;
                System.out.print("Ingrese nuevo Telefono: ");
                telefono = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nDato no valido, vuelva a ingresar el nuevo Telefono");
                datoInvalido = true;
                sc.nextLine();
            }
        } while (datoInvalido);    

        ClienteDao.modificarCliente(cliente);
        System.out.println("\nDatos del cliente actualizados.\n");
        
        List<Reserva> reservas = cliente.getReserva();
        if (reservas != null) { 
            for (Reserva reser : reservas) {
                reser.setCliente(cliente);
                ReservaDao.modificarReserva(reser);
            }
        } else {
            System.out.println("El cliente no tiene reservas asociadas.");
        }
    }

    public static void consultaClientes(Scanner sc) {
        System.out.println("========== CONSULTA CLIENTES ==========");
        try {
            List<Cliente> clientes = ClienteDao.obtenerTodasLosClientes(); 

            if (clientes.isEmpty()) {
                System.out.println("No hay ningún cliente registrado.");
            } else {
                System.out.println("Lista de Clientes:");
                ClienteDao.mostrarTodosLosClientes();
                
            }
        } catch (Exception e) {
            System.out.println("Error al mostrar los Clientes");
            System.out.println(e.getMessage());
        }
    }

    public static void consultaSalones(Scanner sc) {
        System.out.println("========== CONSULTA SALONES ==========");
        SalonDao.guardarSalones();
        try {
            System.out.println("Lista de Salones:");
            SalonDao.mostrarTodosLosSalones();
        } catch (Exception e) {
            System.out.println("Error al mostrar los Salones");
            System.out.println(e.getMessage());
        }
    }

    public static void consultarReserva(Long numId) {
        Reserva reser = ReservaDao.buscarReservaPorId(numId);
        if (reser != null) { 
            reser.mostrarDatos();
        } else {
            System.out.println("Reserva no encontrada.");
        }
    }

 
    public static void consultarTodasLasReservas(Scanner sc) {
        System.out.println("========== CONSULTA TODAS LAS RESERVAS ==========");
        try {
        	List<Reserva> reservas = ReservaDao.obtenerTodasLasReservas();
        	if(reservas.isEmpty()) {
        	System.out.println("No hay ninguna reserva realizada!");
        	}else {
            System.out.println("Lista de Reservas Realizadas: ");
            ReservaDao.mostrarTodosLasReservas();
        	}
        } catch (Exception e) {
            System.out.println("Error al mostrar las Reservas");
            System.out.println(e.getMessage());
        }
    }

    public static void consultaServiciosAdicionales(Scanner sc) {
        System.out.println("========== CONSULTA SERVICIOS ADICIONALES ==========");
        ServiciosAdicionalesDao.guardarServicio();
        try {
            System.out.println("Lista de Servicios Adicionales: ");
            ServiciosAdicionalesDao.mostrarTodosLosServiciosAdicionales();
        } catch (Exception e) {
            System.out.println("Error al mostrar los Servicios Adicionales");
            System.out.println(e.getMessage());
        }
    }
}
