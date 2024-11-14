package ar.edu.unju.escmi.tpfinal.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtil {
    
    private static final String FORMATO_FECHA = "dd/MM/yyyy";
    private static final String FORMATO_HORA = "HH:mm";
    
    public static LocalDate stringToLocalDate(String fechaStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return LocalDate.parse(fechaStr, formatter);
    }
    
    public static String localDateToString(LocalDate fecha) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);
        return fecha.format(formatter);
    }
    
    public static LocalTime stringToLocalTime(String horaStr) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_HORA);
        return LocalTime.parse(horaStr, formatter);
    }
    
    public static String localTimeToString(LocalTime hora) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_HORA);
        return hora.format(formatter);
    }
    
    public static boolean isHorarioValido(LocalTime horaInicio, LocalTime horaFin) {
        LocalTime horarioApertura = LocalTime.of(10, 0);
        LocalTime horarioCierre = LocalTime.of(23, 0);
        
        return !horaInicio.isBefore(horarioApertura) && 
               !horaFin.isAfter(horarioCierre) && 
               horaInicio.isBefore(horaFin);
    }
    
    public static int calcularHorasExtra(LocalTime horaInicio, LocalTime horaFin) {
        int horasTotal = horaFin.getHour() - horaInicio.getHour();
        return Math.max(0, horasTotal - 4); // 4 son las horas base incluidas
    }
}