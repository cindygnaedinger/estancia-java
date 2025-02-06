import entidades.Clientes;
import persistencia.ClienteDAO;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            ClienteDAO clienteDAO = new ClienteDAO();

            Clientes cliente = new Clientes(
                "Mauro", 
                "Independencia", 
                351, 
                "5000", 
                "Córdoba", 
                "Argentina",
                "mauro@mail.com"
            );

            clienteDAO.guardarCliente(cliente);
            System.out.println("Cliente guardado correctamente.");

            System.out.println("Listado de clientes:");
            clienteDAO.listarTodosLosClientes().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error: " + e.getMessage());
        }
    }
}
