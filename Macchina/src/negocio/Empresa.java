package negocio;
import datos.*;

import java.util.ArrayList;
import java.io.Serializable;

public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;

    private String nombre;
    private ArrayList<Cliente> clientes;
    private ArrayList<Venta> ventas;
    private ArrayList<Pedido> pedidos;
    private ArrayList<Autoparte> autopartes;

    public Empresa(String nombre) {
        this.nombre = nombre;
        this.clientes = new ArrayList<>();
        this.ventas = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.autopartes = new ArrayList<>();
    }

    public static Empresa recuperarse() {
        Empresa emp = (Empresa)Datos.recuperar();
        if (emp == null)
            emp = new Empresa("LA EMPRESA S.A.");
        return emp;
    }

    public boolean guardar() {
        return Datos.guardar(this);
    }

    // Métodos para gestionar clientes
    public void agregarCliente(Cliente cliente) {
        this.clientes.add(cliente);
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
        if (cliente != null) {
            int index = clientes.indexOf(cliente);
            clientes.set(index, clienteModificado);
        }
    }

    public void eliminarCliente(int id) {
        Cliente cliente = buscarCliente(id);
        if (cliente != null) {
            clientes.remove(cliente);
        }
    }

    // Métodos para gestionar ventas
    public void agregarVenta(Venta venta) {
        this.ventas.add(venta);
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

    // Métodos para gestionar pedidos
    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
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

    public void modificarPedido(int id, Pedido pedidoModificado) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId_Pedido() == id) {
                pedidos.set(i, pedidoModificado);
                return;
            }
        }
    }
    
    // Métodos para gestionar autopartes
    public void agregarAutoparte(Autoparte autoparte) {
        this.autopartes.add(autoparte);
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
        if (autoparte != null) {
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
    
}

