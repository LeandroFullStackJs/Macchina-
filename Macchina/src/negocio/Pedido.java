package negocio;
import java.io.Serializable;
import java.util.ArrayList;
//import java.util.Date;

public class Pedido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_Pedido; 
	private String fecha; // Podria ser Date . 
	private double montoTotal; 
	private int cantidad ;
	private Cliente cliente ; 
	//private autopartesPedidas: Autoparte[]
	private ArrayList<DetallePedido> detalles;
	private boolean cancelado ;
	
	public Pedido(int id_Pedido, String fecha, double montoTotal, int cantidad, Cliente cliente, boolean cancelado) {
		super();
		this.id_Pedido = id_Pedido;
		this.fecha = fecha;
		this.montoTotal = montoTotal;
		this.cantidad = cantidad;
		this.cliente = cliente;
		this.cancelado = cancelado;
		this.detalles = new  ArrayList<DetallePedido>();
	}

	 public void calcularMontoTotal() {
	        montoTotal = 0;
	        for (DetallePedido detalle : detalles) {
	            montoTotal += detalle.getPrecio() * detalle.getCantidad();
	        }
	 }

	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int getId_Pedido() {
		return id_Pedido;
	}

	public void setId_Pedido(int id_Pedido) {
		this.id_Pedido = id_Pedido;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public ArrayList<DetallePedido> getDetalles() {
		return detalles;
	}

	public void setDetalles(ArrayList<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}
	 
	@Override
    public String toString() {
        return "Pedido{" +
                "id=" + id_Pedido +
                ", fecha='" + fecha + '\'' +
                ", montoTotal=" + montoTotal +
                ", cantidad=" + cantidad +
                ", cliente=" + cliente +
                ", cancelado=" + cancelado +
                '}';
    }
	 
	 
}
