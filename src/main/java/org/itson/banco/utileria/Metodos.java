package org.itson.banco.utileria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase de utilidad que proporciona métodos estáticos para la manipulación, 
 * conversión y cálculo de fechas dentro del sistema bancario.
 * Centraliza la lógica de transformación entre formatos de la interfaz de usuario,
 * objetos de negocio y la persistencia en MySQL.
 * @author Dario
 */
public class Metodos {
    
    /**
    * Constructor privado para evitar la instanciación de la clase de utilería.
    */
    private Metodos() {
        throw new UnsupportedOperationException("Esta es una clase de utilería.");
    }
    
    /**
     * Convierte un objeto GregorianCalendar a una cadena de texto .
     * @param fecha Objeto de fecha a formatear.
     * @return String con formato ISO 8601 o {@code null} si la fecha es nula.
     */
    public static String formatearSQL(GregorianCalendar fecha) {
        if (fecha == null) return null;
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(fecha.getTime());
    }

    /**
     * Calcula la edad cronológica basándose en una fecha de nacimiento.
     * Compara el año, mes y día de la fecha proporcionada contra la fecha actual 
     * del sistema para determinar si el usuario ya cumplió años en el ciclo actual.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     * @return Edad en años (int). Si la fecha es nula, retorna 0.
     */
    public static int calcularEdad(GregorianCalendar fechaNacimiento) {
        if (fechaNacimiento == null) return 0;
        GregorianCalendar hoy = new GregorianCalendar();
        int edad = hoy.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);

        int mesActual = hoy.get(Calendar.MONTH);
        int mesNacimiento = fechaNacimiento.get(Calendar.MONTH);

        // Validación de si ya pasó el cumpleaños en el año actual
        if (mesActual < mesNacimiento || 
           (mesActual == mesNacimiento && hoy.get(Calendar.DAY_OF_MONTH) < fechaNacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad;
    }
    
    /**
     * Transforma un objeto java.sql.Date proveniente de la base de datos 
     * a un objeto GregorianCalendar para su uso en la capa de negocio.
     * @param fechaSQL Fecha obtenida de un ResultSet.
     * @return Objeto GregorianCalendar o  null si el parámetro es nulo.
     */
    public static GregorianCalendar convertirSqlADate(java.sql.Date fechaSQL) {
        if (fechaSQL == null) return null;
        GregorianCalendar fechaNac = new GregorianCalendar();
        fechaNac.setTime(fechaSQL);
        return fechaNac;
    }
    
    /**
     * Convierte una cadena de texto (típicamente de un JFormattedTextField) 
     * con formato "dd/MM/yyyy" a un objeto GregorianCalendar.
     * @param fechaTexto Texto que representa la fecha.
     * @return Objeto GregorianCalendar correspondiente.
     * @throws ParseException Si la fecha está incompleta o tiene un formato inválido 
     * (ej. 31/02/2024 gracias al modo setLenient(false)).
     */
   public static GregorianCalendar convertirStringAGregorian(String fechaTexto) throws ParseException {
        if (fechaTexto == null || fechaTexto.contains("_")) {
            throw new ParseException("Fecha incompleta", 0);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Estricto: no permite días inexistentes

        Date date = sdf.parse(fechaTexto);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        return cal;
   }
}
