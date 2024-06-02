package negocio;

public class DetallePedido {

	
	private int id;
    private Autoparte Autoparte;
    private double precio;
    private int cantidad;
    
    public DetallePedido(int id, negocio.Autoparte autoparte, double precio, int cantidad) {
		super();
		this.id = id;
		Autoparte = autoparte;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Autoparte getAutoparte() {
		return Autoparte;
	}

	public void setAutoparte(Autoparte autoparte) {
		Autoparte = autoparte;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
    
    
}
