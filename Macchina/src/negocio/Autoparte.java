package negocio;

public class Autoparte {
	
	
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
	// SOLO ME PIDE EL CIRCUITO DE PEDIDO Y VENTA , HACER LOS SIGUIENTES METODO SI QUEDA TIEMPO : 
		public void listarAutoparte() {
	        // Implementar la logica para listar autopartes 
			// TAL VEZ SOLO CON MOSTRAR LA LISTA DE AUTOPARTES EN LA CLASE VEGICULO YA ESTARIA . 
	    } 
		
		public void LlegoAlStockMin() {
			// ESTE TAL VEZ SI HAY QUE IMPLEMENTARLO 
		}
		
		public void UpdateAutoaprte() {
			
		}
		
		public void DeleteAutoparte() {
			
		}
		
		public void UpdateStock( int codigo) {
			
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
		this.stock = stock;
	}

	public int getStock_minimo() {
		return stock_minimo;
	}

	public void setStock_minimo(int stock_minimo) {
		this.stock_minimo = stock_minimo;
	}

	
	
	
}
