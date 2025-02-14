package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;
import entidades.Estancia;

public class EstanciaDAO extends DAO {

    public void guardarEstancia(Estancia estancia) throws Exception {
        if (estancia == null) {
            throw new Exception("La estancia no puede ser nula.");
        }

        String sql = "INSERT INTO estancias (id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta) VALUES (?,?,?,?,?)";

        try {
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, estancia.getIdCliente());
            preparedStatement.setInt(2, estancia.getIdCasa());
            preparedStatement.setString(3, estancia.getNombreHuesped());
            preparedStatement.setDate(4, Date.valueOf(estancia.getFechaDesde()));
            preparedStatement.setDate(5, Date.valueOf(estancia.getFechaHasta()));

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Estancia> listarTodasLasEstancias() throws Exception {
        List<Estancia> estancias = new ArrayList<>();
        String sql = "SELECT id_estancia, id_cliente, id_casa, nombre_huesped, fecha_desde, fecha_hasta FROM estancias";

        try {
            resultSet = consultarDataBase(sql);

            while (resultSet.next()) {
                Estancia estancia = new Estancia();
                estancia.setIdEstancia(resultSet.getInt("id_estancia"));
                estancia.setIdCliente(resultSet.getInt("id_cliente"));
                estancia.setIdCasa(resultSet.getInt("id_casa"));
                estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
                estancia.setFechaDesde(resultSet.getDate("fecha_desde").toLocalDate());
                estancia.setFechaHasta(resultSet.getDate("fecha_hasta").toLocalDate());

                estancias.add(estancia);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            desconectarDataBase();
        }

        return estancias;
    }

    public void eliminarEstancia(int idEstancia) throws Exception {
        String sql = "DELETE FROM estancias WHERE id_estancia = " + idEstancia;
        insertarModificarEliminarDataBase(sql);
    }

    public Estancia buscarEstanciaPorId(int idEstancia) throws Exception {
        try {
            String sql = "SELECT * FROM estancias WHERE id_estancia = " + idEstancia;
            consultarDataBase(sql);
            Estancia estancia = null;
            while (resultSet.next()) {
                estancia = new Estancia();
                estancia.setIdEstancia(resultSet.getInt("id_estancia"));
                estancia.setIdCliente(resultSet.getInt("id_cliente"));
                estancia.setIdCasa(resultSet.getInt("id_casa"));
                estancia.setNombreHuesped(resultSet.getString("nombre_huesped"));
                estancia.setFechaDesde(resultSet.getDate("fecha_desde").toLocalDate());
                estancia.setFechaHasta(resultSet.getDate("fecha_hasta").toLocalDate());
            }
            desconectarDataBase();
            return estancia;
        } catch (Exception e) {
            desconectarDataBase();
            throw e;
        }
    }
}
