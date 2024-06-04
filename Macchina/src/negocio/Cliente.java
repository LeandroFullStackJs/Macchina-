package negocio;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String localidad;
    private String provincia;
    private String email;
    private ArrayList<Pedido> pedidos;  // Lista de pedidos del cliente
    
    public Cliente(int id, String nombre, String direccion, String telefono, String localidad, String provincia,
			String email) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.localidad = localidad;
		this.provincia = provincia;
		this.email = email;
		this.pedidos = new ArrayList<Pedido>();
	}
    
    public void agregarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public void eliminarPedido(int id) {
        this.pedidos.removeIf(pedido -> pedido.getId_Pedido() == id);
    }
	
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	 @Override
	    public String toString() {
	        return "Cliente{" +
	                "id=" + id +
	                ", nombre='" + nombre + '\'' +
	                ", direccion='" + direccion + '\'' +
	                ", telefono='" + telefono + '\'' +
	                '}';
	    }
}
