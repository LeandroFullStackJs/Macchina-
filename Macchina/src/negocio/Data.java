package negocio;

import java.util.ArrayList;

public class Data {
	
	    private static Data instance;
	    private ArrayList<Cliente> clientes;
	    private ArrayList<Autoparte> autopartes;
	    private ArrayList<Pedido> pedidos;
	    private ArrayList<Venta> ventas;
	    
	    private Data() {
	        clientes = new ArrayList<>();
	        autopartes = new ArrayList<>();
	        pedidos = new ArrayList<>();
	        ventas = new ArrayList<>();
	    }
	    
	    public static synchronized Data getInstance() {
	        if (instance == null) {
	            instance = new Data();
	        }
	        return instance;
	    }
	    
	    public void agregarCliente(Cliente cliente) {
	        clientes.add(cliente);
	    }

	    public void eliminarCliente(int id) {
	        clientes.removeIf(cliente -> cliente.getId() == id);
	    }

	    public Cliente buscarCliente(int id) {
	        return clientes.stream().filter(cliente -> cliente.getId() == id).findFirst().orElse(null);
	    }

	    public ArrayList<Cliente> listarClientes() {
	        return new ArrayList<>(clientes);
	    }

	    public void agregarAutoparte(Autoparte autoparte) {
	        autopartes.add(autoparte);
	    }

	    public void eliminarAutoparte(int id) {
	        autopartes.removeIf(autoparte -> autoparte.getId() == id);
	    }

	    public Autoparte buscarAutoparte(int id) {
	        return autopartes.stream().filter(autoparte -> autoparte.getId() == id).findFirst().orElse(null);
	    }

	    public ArrayList<Autoparte> listarAutopartes() {
	        return new ArrayList<>(autopartes);
	    }

	    // TODO Métodos parecidos para pedidos y ventas
	    
	    public void agregarPedido(Pedido pedido) {
	        pedidos.add(pedido);
	        Cliente cliente = buscarCliente(pedido.getCliente().getId());
	        if (cliente != null) {
	            cliente.agregarPedido(pedido);
	        }
	    }

	    public void eliminarPedido(int id) {
	        Pedido pedido = buscarPedido(id);
	        if (pedido != null) {
	            pedidos.remove(pedido);
	            Cliente cliente = buscarCliente(pedido.getCliente().getId());
	            if (cliente != null) {
	                cliente.eliminarPedido(id);
	            }
	        }
	    }

	    public Pedido buscarPedido(int id) {
	        return pedidos.stream().filter(pedido -> pedido.getId_Pedido() == id).findFirst().orElse(null);
	    }

	    public ArrayList<Pedido> listarPedidos() {
	        return new ArrayList<>(pedidos);
	    }

	    public void agregarVenta(Venta venta) {
	        ventas.add(venta);
	    }

	    public void eliminarVenta(int id) {
	        ventas.removeIf(venta -> venta.getId() == id);
	    }

	    public Venta buscarVenta(int id) {
	        return ventas.stream().filter(venta -> venta.getId() == id).findFirst().orElse(null);
	    }

	    public ArrayList<Venta> listarVentas() {
	        return new ArrayList<>(ventas);
	    }
	}

