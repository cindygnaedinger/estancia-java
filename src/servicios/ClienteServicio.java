package servicios;
import entidades.Clientes;
import persistencia.ClienteDAO;

public class ClienteServicio {
    private ClienteDAO cd;
    public ClienteServicio() {
        this.cd = new ClienteDAO();
    }

    public Clientes crearNuevoCliente(String nombre, String calle, int numero, String codigoPostal, String ciudad, String pais, String email) throws Exception {
        // Validaciones - Pueden estar metodo independiente.
        validacionesNyE(nombre, email);

        Clientes cliente = new Clientes(nombre, calle, numero, codigoPostal, ciudad, pais, email);
        cd.guardarCliente(cliente);
        return cliente;
    }

    public void validacionesNyE(String nombre, String email) throws Exception {
        if (nombre == null) {
            throw new Exception("El nombre del contacto no puede ser nulo.");
        }
        if (email == null) {
            throw new Exception("El apellido del contacto no puede ser nulo.");
        }
    }
}