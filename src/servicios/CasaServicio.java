package servicios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidades.Casas;
import persistencia.CasasDAO;  // Corregido el nombre del DAO, usa la convención de nombres.

public class CasaServicio {
    private CasasDAO cd;  // Corregido el tipo de la variable.
    
    public CasaServicio() {
        this.cd = new CasasDAO();  // Corregido el nombre del DAO.
    }

    public Casas crearNuevaCasa(String calle, int numero, String codigoPostal, String ciudad, String pais, 
                                LocalDate fechaDesde, LocalDate fechaHasta, int tiempoMinimo, int tiempoMaximo, 
                                double precioHabitacion, String tipoVivienda) throws Exception {
        // Validaciones - Pueden estar en un método independiente.
        validacionesCasa(ciudad, pais, fechaDesde, fechaHasta, tiempoMinimo, tipoVivienda);

        Casas casa = new Casas(calle, numero, codigoPostal, ciudad, pais, fechaDesde, fechaHasta, 
                               tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda);
        cd.create(casa);  // Asegúrate de que el método `create` esté implementado en tu DAO.
        return casa;
    }

    public void validacionesCasa(String ciudad, String pais, LocalDate fechaDesde, LocalDate fechaHasta, 
                                  int tiempoMinimo, String tipoVivienda) throws Exception {
        if (ciudad == null || pais == null || tipoVivienda == null) {
            throw new Exception("El campo no puede ser nulo");
        }
        if (tiempoMinimo <= 0) {
            throw new Exception("El tiempo mínimo debe ser mayor a 0");
        }
        if (fechaHasta.isBefore(fechaDesde)) {
            throw new Exception("La fecha de fin no puede ser anterior a la fecha de inicio");
        }
    }

    public List<Casas> ejercicioDos() {
        List<Casas> allCasas = cd.findAll();  // Método findAll debe existir en el DAO.
        List<Casas> filtradas = new ArrayList<>();

        LocalDate fechaInicio = LocalDate.parse("2020-08-01");
        LocalDate fechaFin = LocalDate.parse("2020-08-31");

        for (Casas casa : allCasas) {
            if (casa.getPais().equalsIgnoreCase("Reino Unido")) {
                // Verifica si la casa está disponible en el rango de fechas solicitado.
                if (!(casa.getFechaDesde().isAfter(fechaFin) || casa.getFechaHasta().isBefore(fechaInicio))) {
                    filtradas.add(casa);
                }
            }
        }
        return filtradas;
    }
}

