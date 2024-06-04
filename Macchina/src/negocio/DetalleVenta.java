package negocio;

import java.io.Serializable;

public class DetalleVenta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private Autoparte autoparte;
    private double precio;
    private int cantidad;
    
    public DetalleVenta(int id, Autoparte autoparte, double precio, int cantidad) {
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
        return "DetalleVenta{id=" + id +
                ", autoparte=" + (autoparte != null ? autoparte.getDenominacion() : "N/A") +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precio +
                '}';
    }
}
