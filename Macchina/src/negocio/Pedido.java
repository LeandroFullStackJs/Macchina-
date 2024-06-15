package negocio;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class Pedido implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	    private int id_Pedido; 
	    private Date fecha; // Cambiado a Date 
	    private double montoTotal; 
	    private Cliente cliente; 
	   // private List<Autoparte> autopartes;
	    private ArrayList<DetallePedido> detalles;
	    private boolean cancelado;
	    private int siguienteIdDetalle; // agregado
	    private FormaDePago formaDePago;
	    
	    // Constructor principal
	    public Pedido(int id_Pedido, Date fecha, Cliente cliente ) {
	        this.id_Pedido = id_Pedido;
	        this.fecha = fecha;
	        this.montoTotal = 0;
	        this.cliente = cliente;
	       // this.autopartes = autopartes;
	        this.detalles = new ArrayList<>();
	        this.cancelado = false;
	    }

	    // Método para agregar un detalle al pedido
	    public void agregarDetalle(DetallePedido detalle) {
	    	detalle.setId(siguienteIdDetalle++);
	        this.detalles.add(detalle);
	        this.calcularMontoTotal();
	    }

	    // Método para eliminar un detalle del pedido
	    public void eliminarDetalle(DetallePedido detalle) {
	        this.detalles.remove(detalle);
	        this.calcularMontoTotal();
	    }

	    // Método para calcular el monto total del pedido
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

	    public Date getFecha() {
	        return fecha;
	    }

	    public void setFecha(Date fecha) {
	        this.fecha = fecha;
	    }

	    public double getMontoTotal() {
	        return montoTotal;
	    }

	    public void setMontoTotal(double montoTotal) {
	        this.montoTotal = montoTotal;
	    }

	    public ArrayList<DetallePedido> getDetalles() {
	        return detalles;
	    }

	    public void setDetalles(ArrayList<DetallePedido> detalles) {
	        this.detalles = detalles;
	        this.calcularMontoTotal();
	    }

	    public boolean isCancelado() {
	        return cancelado;
	    }

	    public void setCancelado(boolean cancelado) {
	        this.cancelado = cancelado;
	    }
	    
	    
	    /*public List<Autoparte> getAutopartes() {
			return autopartes;
		}

		public void setAutopartes(List<Autoparte> autopartes) {
			this.autopartes = autopartes;
		} */

	    
		public FormaDePago getFormaDePago() {
			return formaDePago;
		}

		public void setFormaDePago(FormaDePago formaDePago) {
			this.formaDePago = formaDePago;
		}

		@Override
	    public String toString() {
	        return "Pedido{" +
	                "id=" + id_Pedido +
	                ", fecha=" + fecha +
	                ", montoTotal=" + montoTotal +
	                ", cliente=" + cliente +
	                ", cancelado=" + cancelado +
	                ", detalles=" + detalles +
	                '}';
	    }
		// Método para obtener los nombres de las autopartes en el pedido
		   public String getAutopartesNombres() {
		        StringBuilder nombres = new StringBuilder();
		        for (DetallePedido detalle : detalles) {
		            nombres.append(detalle.getAutoparte().getDenominacion()).append(", ");
		        }
		        if (nombres.length() > 0) {
		            nombres.setLength(nombres.length() - 2); // saca la ultima coma . 
		        }
		        return nombres.toString();
		    }
	 
}
