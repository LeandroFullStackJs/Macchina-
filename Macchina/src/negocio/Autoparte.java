package negocio;

import java.io.Serializable;

public class Autoparte implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; 
	private String denominacion; 
	private String descripcion; 
	private int categoria; 
	//private String marca; 
	private Vehiculo vehiculo; 
	private double precio_unitario; 
	private String enlace; 
	private int stock; 
	private int stock_minimo; 

	public Autoparte(int id, String denominacion, String descripcion, int categoria,Vehiculo vehiculo,
			double precio_unitario, String enlace, int stock, int stock_minimo) {
		//super();
		this.id = id;
		this.denominacion = denominacion;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.vehiculo = vehiculo;
		this.precio_unitario = precio_unitario;
		this.enlace = enlace;
		this.stock = stock;
		this.stock_minimo = stock_minimo;
	}

		  @Override
		    public String toString() {
		        return "Autoparte [id=" + id + ", denominacion=" + denominacion + ", descripcion=" + descripcion + 
		               ", categoria=" + categoria + ", vehiculo=" + vehiculo  + 
		               ", precio_unitario=" + precio_unitario + ", enlace=" + enlace + ", cantidadEnStock=" + stock + 
		               ", stock_minimo=" + stock_minimo + "]";
		    }
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getCategoria() {
		return categoria;
	}

	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public double getPrecio_unitario() {
		return precio_unitario;
	}

	public void setPrecio_unitario(double precio_unitario) {
		if (precio_unitario < 0) {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo");
        }
		this.precio_unitario = precio_unitario;
	}

	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        }
		this.stock = stock;
	}

	public int getStock_minimo() {
		
		return stock_minimo;
	}

	public void setStock_minimo(int stock_minimo) {
		if (stock_minimo < 0) {
            throw new IllegalArgumentException("El stock mínimo no puede ser negativo");
        }
		this.stock_minimo = stock_minimo;
	}
}
