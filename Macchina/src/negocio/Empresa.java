package negocio;
import datos.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import java.io.Serializable;

public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Cliente> clientes;
    private ArrayList<Venta> ventas;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Autoparte> autopartes;
    private int contadorIdDetalleVenta = 1; // Contador para generar IDs  para los detalles de venta.
    private int siguienteIdPedido; // Contador para generar IDs para los pedidos .
    private int siguienteIdVenta; // Contador para generar IDs para las ventas . 
    
    public Empresa(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.autopartes = new ArrayList<>();
        this.siguienteIdPedido = 1; // Inicializa el ID de pedidos en 1 .
        this.siguienteIdVenta = 1; // Inicializa el ID de ventas en 1 . 

    }

    public static Empresa recuperarse() { // Metodo estático para recuperar una instancia de Empresa desde el almacenamiento , Por eso utilizo Datos.recuperar() . 
        Empresa emp = (Empresa)Datos.recuperar();
        if (emp == null)
            emp = new Empresa("Tutta la Machinna");
        return emp;
    }

    public boolean guardar() { // Guardar la instancia actual 
        return Datos.guardar(this);
    }

    // Métodos para gestionar clientes
    public void agregarCliente(Cliente cliente) {
    	if (cliente != null && buscarCliente(cliente.getId()) == null) {
            this.clientes.add(cliente);
        }
    }

    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    public Cliente buscarCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }

    public void modificarCliente(int id, Cliente clienteModificado) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null && clienteModificado != null) {
            int index = clientes.indexOf(cliente);
            clientes.set(index, clienteModificado);
        }
    }

    /* public void eliminarCliente(int id) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            clientes.remove(cliente);
        }
    } */ // No hay que hacer un eliminado fisico de los clientes. 

    // Métodos para gestionar ventas
    public void agregarVenta(Venta venta) {
    	if (venta != null && buscarVenta(venta.getId()) == null) {
            this.ventas.add(venta);
        }
    }

 // Método para modificar una venta existente
    public void modificarVenta(int indice, Venta nuevaVenta) {
        
            this.ventas.set(indice, nuevaVenta);
        
    }

    // Método para eliminar una venta existente
    public void eliminarVenta(int id) {
        
    	Venta venta = buscarVenta(id);
        if (venta != null) {
            ventas.remove(venta);
        } else {
            throw new IndexOutOfBoundsException("Venta con ID " + id + " no encontrada.");
        }
        
    }
    
    public ArrayList<Venta> listarVentas() {
        return new ArrayList<>(ventas);
    }

    public Venta buscarVenta(int id) {
        for (Venta venta : ventas) {
            if (venta.getId() == id) {
                return venta;
            }
        }
        return null;
    }
    
 // Método para convertir un pedido a venta
    public void convertirPedidoAVenta(int pedidoId) {
        Pedido pedido = null;

        // Buscar el pedido por ID
        for (Pedido p : pedidos) {
            if (p.getId_Pedido() == pedidoId) {
                pedido = p;
                break;
            }
        }

        if (pedido != null) {
            // Crear una nueva venta a partir del pedido
            Venta nuevaVenta = Venta.realizarVentaPedido(generarIdVenta(), pedido, pedido.getFormaDePago());

            // Agregar la nueva venta a la lista de ventas
            agregarVenta(nuevaVenta);

            // Eliminar el pedido de la lista de pedidos (si es necesario)
            pedidos.remove(pedido);
        } else {
            throw new IllegalArgumentException("Pedido con ID " + pedidoId + " no encontrado.");
        }
    }

    // Método para generar un nuevo ID de venta
    public int generarIdVenta() {
        return siguienteIdVenta++;
    }

    // Métodos para gestionar pedidos
    public void agregarPedido(Pedido pedido) {
    	if (pedido != null && buscarPedido(pedido.getId_Pedido()) == null) {
    		pedido.setId_Pedido(siguienteIdPedido++);
            this.pedidos.add(pedido);
        }
    }
    public List<Autoparte> buscarAutopartesPorNombres(String nombres) {
        String[] nombresArray = nombres.split(",");
        List<Autoparte> resultado = new ArrayList<>();
        for (String nombre : nombresArray) {
            for (Autoparte autoparte : autopartes) {
                if (autoparte.getDenominacion().equalsIgnoreCase(nombre.trim())) {
                    resultado.add(autoparte);
                }
            }
        }
        return resultado;
    }

    public ArrayList<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    public Pedido buscarPedido(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId_Pedido() == id) {
                return pedido;
            }
        }
        return null;
    }

    public void cancelarPedido(int id) {
        Pedido pedido = buscarPedido(id);
        if (pedido != null) {
            for (DetallePedido detalle : pedido.getDetalles()) {
                Autoparte autoparte = detalle.getAutoparte();
                autoparte.setStock(autoparte.getStock() + detalle.getCantidad());
            }
            pedidos.remove(pedido);
        }
    }

   /* public void modificarPedido(int id, Pedido pedidoModificado) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId_Pedido() == id) {
                pedidos.set(i, pedidoModificado);
                return;
            }
        }
    } */
    
    public void modificarPedido(Pedido pedido) {
        int index = pedidos.indexOf(buscarPedido(pedido.getId_Pedido()));
        if (index != -1) {
            pedidos.set(index, pedido);
        }
    }
    
    // Métodos para gestionar autopartes
    public void agregarAutoparte(Autoparte autoparte) {
    	if (autoparte != null && buscarAutoparte(autoparte.getId()) == null) {
            this.autopartes.add(autoparte);
        }
    }

    public ArrayList<Autoparte> listarAutopartes() {
        return new ArrayList<>(autopartes);
    }

    public Autoparte buscarAutoparte(int id) {
        for (Autoparte autoparte : autopartes) {
            if (autoparte.getId() == id) {
                return autoparte;
            }
        }
        return null;
    }

    public void modificarAutoparte(int id, Autoparte autoparteModificada) {
        Autoparte autoparte = buscarAutoparte(id);
        if (autoparte != null && autoparteModificada != null) {
            int index = autopartes.indexOf(autoparte);
            autopartes.set(index, autoparteModificada);
        }
    }

    public void eliminarAutoparte(int id) {
        Autoparte autoparte = buscarAutoparte(id);
        if (autoparte != null) {
            autopartes.remove(autoparte);
        }
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        } else {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }
    
    public synchronized int generarIdDetalleVenta() { // Metodo sincronizado para generar un nuevo ID de detalle de venta
        return contadorIdDetalleVenta++;
    }
    
    public void disminuirStock(DetalleVenta detalle) { // Método para disminuir el stock de una autoparte basandome en un detalle de venta
        Autoparte autoparte = detalle.getAutoparte();
        int nuevaCantidad = autoparte.getStock() - detalle.getCantidad();
        if (nuevaCantidad < 0) {
            throw new IllegalArgumentException("Stock insuficiente para la autoparte: " + autoparte.getDenominacion());
        }
        autoparte.setStock(nuevaCantidad);
        
     // Verificar si el stock ha alcanzado el stock mínimo y avisar por pantalla con un showMessageDialog . 
        if (nuevaCantidad < autoparte.getStock_minimo()) {
            JOptionPane.showMessageDialog(null, "El stock de la autoparte " + autoparte.getDenominacion() + " ha alcanzado el nivel mínimo.", "Advertencia de Stock Mínimo", JOptionPane.WARNING_MESSAGE);
        }
        
        
    }
}

