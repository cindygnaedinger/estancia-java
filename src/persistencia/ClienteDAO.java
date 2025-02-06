package persistencia;

import java.util.ArrayList;
import java.util.List;

import entidades.Clientes;

public class ClienteDAO extends DAO {
    public void guardarCliente(Clientes cliente) throws Exception {
        if(cliente == null) {
            throw new Exception("El cliente no puede ser nulo");
        }

        String sql = "INSERT INTO clientes (nombre, calle, numero, codigo_postal, ciudad, pais, email) VALUES(?,?,?,?,?,?,?)";

        try {
            connectarDataBase();
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNombre());
            preparedStatement.setString(2, cliente.getCalle());
            preparedStatement.setInt(3, cliente.getNumero());
            preparedStatement.setString(4, cliente.getCodigoPostal());
            preparedStatement.setString(5, cliente.getCiudad());
            preparedStatement.setString(6, cliente.getPais());
            preparedStatement.setString(7, cliente.getEmail());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarDataBase();
        }
    }

    public List<Clientes> listarTodosLosClientes() throws Exception {
        List<Clientes> clientes = new ArrayList<>();
        String sql = "SELECT nombre, calle, numero, codigo_postal, ciudad, pais, email FROM clientes";

        try {
            resultSet = consultarDataBase(sql);

            while(resultSet.next()) {
                Clientes cliente = new Clientes();
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getInt("numero"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));

                clientes.add(cliente);

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            desconectarDataBase();
        }

        return clientes;
    }

    public void eliminarCliente(int idCliente) throws Exception {
        String sql = "DELETE FROM clientes WHERE idCliente = " + idCliente;
        insertarModificarEliminarDataBase(sql);
    }

    public Clientes buscarClientePorId(int idCliente) throws Exception {
        try {
            String sql = "SELECT * FROM clientes WHERE idCliente = " + idCliente;
            consultarDataBase(sql);
            Clientes cliente = null;
            while (resultSet.next()) {
                cliente = new Clientes();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCalle(resultSet.getString("calle"));
                cliente.setNumero(resultSet.getInt("numero"));
                cliente.setCodigoPostal(resultSet.getString("codigo_postal"));
                cliente.setCiudad(resultSet.getString("ciudad"));
                cliente.setPais(resultSet.getString("pais"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.toString();
            }
            desconectarDataBase();
            return cliente;
        } catch (Exception e) {
            desconectarDataBase();
            throw e;
        }
    }
}
