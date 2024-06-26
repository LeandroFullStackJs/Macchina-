package negocio;

import java.io.Serializable;

public class DetallePedido implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private Autoparte autoparte;
    private double precio;
    private int cantidad;
    
    public DetallePedido(int id, Autoparte autoparte, double precio, int cantidad) {
		super();
		this.id = id;
		this.autoparte = autoparte;
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
		return autoparte;
	}

	public void setAutoparte(Autoparte autoparte) {
		this.autoparte = autoparte;
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
    
	@Override
    public String toString() {
        return "DetallePedido{id=" + id +
                ", autoparte=" + autoparte +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
