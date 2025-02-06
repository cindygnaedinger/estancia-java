package persistencia;

import java.util.ArrayList;
import java.util.List;

import entidades.Casas;

public class casasDAO extends DAO {

    public void insertarCasa(Casas casa) throws Exception {
        String sql = "INSERT INTO casas (calle, numero, codigoPostal, ciudad, pais, fechaDesde, fechaHasta, tiempoMinimo, tiempoMaximo, precioHabitacion, tipoVivienda) VALUES ('"
                +
                casa.getCalle() + "', '" +
                casa.getNumero() + "', '" +
                casa.getCodigoPostal() + "', '" +
                casa.getCiudad() + "', '" +
                casa.getPais() + "', '" +
                casa.getFechaDesde() + "', '" +
                casa.getFechaHasta() + "', '" +
                casa.getTiempoMinimo() + "', '" +
                casa.getTiempoMaximo() + "', '" +
                casa.getPrecioHabitacion() + "', '" +
                casa.getTipoVivienda() + "')";
        insertarModificarEliminarDataBase(sql);
    }

    public void listarTodasLasCasas() throws Exception {
        try {
            String sql = "SELECT idCasa, calle, numero, codigoPostal, ciudad, pais FROM casas";
            consultarDataBase(sql);
            Casas casa = null;
            List<Casas> casas = new ArrayList<>();
            while (resultSet.next()) {
                casa = new Casas();
                casa.setIdCasa(resultSet.getInt("idCasa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigoPostal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casas.add(casa);
            }
            for (Casas casaUnidad : casas) {
                System.out.println(casaUnidad.imprimirDatosCasa());
            }
            System.out.println("");
            desconectarDataBase();
        } catch (Exception e) {
            e.printStackTrace();
            desconectarDataBase();
            throw e;
        }
    }

    public void eliminarCasa(int idCasa) throws Exception {
        String sql = "DELETE FROM casas WHERE idCasa = " + idCasa;
        insertarModificarEliminarDataBase(sql);
    }

    public Casas buscarCasaPorCodigo(int idCasa) throws Exception {
        try {
            String sql = "SELECT * FROM casas WHERE idCasa = " + idCasa;
            consultarDataBase(sql);
            Casas casa = null;
            while (resultSet.next()) {
                casa = new Casas();
                casa.setIdCasa(resultSet.getInt("idCasa"));
                casa.setCalle(resultSet.getString("calle"));
                casa.setNumero(resultSet.getInt("numero"));
                casa.setCodigoPostal(resultSet.getString("codigoPostal"));
                casa.setCiudad(resultSet.getString("ciudad"));
                casa.setPais(resultSet.getString("pais"));
                casa.setFechaDesde(resultSet.getDate("fechaDesde"));
                casa.setFechaHasta(resultSet.getDate("fechaHasta"));
                casa.setTiempoMinimo(resultSet.getInt("tiempoMinimo"));
                casa.setTiempoMaximo(resultSet.getInt("tiempoMaximo"));
                casa.setPrecioHabitacion(resultSet.getDouble("precioHabitacion"));
                casa.setTipoVivienda(resultSet.getString("tipoVivienda"));
                casa.toString();
            }
            desconectarDataBase();
            return casa;
        } catch (Exception e) {
            desconectarDataBase();
            throw e;
        }
    }

}

