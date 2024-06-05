package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente  implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    
    
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String telefono;
    private String localidad;
    private String provincia;
    private String email;
    private List<Pedido> pedidos;  // Lista de pedidos del cliente
    
    // Constructor
    public Cliente(int id, String nombre, String apellido, String dni, String direccion, String telefono, String localidad, String provincia, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.localidad = localidad;
        this.provincia = provincia;
        this.email = email;
    }
    
    public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void agregarPedido(Pedido pedido) {
    	if (pedido != null) {
            this.pedidos.add(pedido);
        }
    }

    public void eliminarPedido(int id) {
        this.pedidos.removeIf(pedido -> pedido.getId_Pedido() == id);
    }
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		if (id > 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
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
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		if (direccion != null && !direccion.trim().isEmpty()) {
            this.direccion = direccion;
        } else {
            throw new IllegalArgumentException("La dirección no puede estar vacía.");
        }
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		if (telefono != null && !telefono.trim().isEmpty()) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		if (localidad != null && !localidad.trim().isEmpty()) {
            this.localidad = localidad;
        } else {
            throw new IllegalArgumentException("La localidad no puede estar vacía.");
        }
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		if (provincia != null && !provincia.trim().isEmpty()) {
            this.provincia = provincia;
        } else {
            throw new IllegalArgumentException("La provincia no puede estar vacía.");
        }
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if (email != null && !email.trim().isEmpty()) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("El email no puede estar vacío.");
        }
	}

	@Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
