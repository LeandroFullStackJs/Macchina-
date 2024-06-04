package negocio;

import java.util.ArrayList;
import java.util.Date;

public class Venta {

		private int id;
	    private Pedido pedido;
	    private Date fecha;
	    private ArrayList<DetalleVenta> detalles;
	    private FormaDePago formaDePago;
	    private double montoFinal;
	    private boolean ventaDirecta;
	    private String nombreClienteDirecta;
	    
	 // Constructor principal
	    public Venta(int id, Date fecha, FormaDePago formaDePago) {
	        this.id = id;
	        this.fecha = fecha;
	        this.detalles = new ArrayList<DetalleVenta>();
	        this.formaDePago = formaDePago;
	        this.calcularMontoFinal();
	    }

	    public Venta(int idVentaDirecta, String nombreClienteDirecta, FormaDePago medioDePagoDirecta) {
			// TODO Auto-generated constructor stub
	   this.id = idVentaDirecta;
	   this.nombreClienteDirecta = nombreClienteDirecta;
	   this.formaDePago = medioDePagoDirecta ;
	    	// REVISAR 
		}

		// Métodos estáticos para crear instancias de Venta
	   // public static Venta cargarVentaDirecta(int id, ArrayList<DetalleVenta> detalles, FormaDePago formaDePago) {
	    public static Venta cargarVentaDirecta(int id,  FormaDePago formaDePago) {
	        return new Venta(id, new Date(), formaDePago);
	    }
	    
	    public static Venta realizarVentaPedido(int id, Pedido pedido, FormaDePago formaDePago) {
	        ArrayList<DetalleVenta> detalles = new ArrayList<>();
	        for (DetallePedido detallePedido : pedido.getDetalles()) {
	            DetalleVenta detalleVenta = new DetalleVenta(
	                detallePedido.getId(),
	                detallePedido.getAutoparte(),
	                detallePedido.getPrecio(),
	                detallePedido.getCantidad()
	            );
	            detalles.add(detalleVenta);
	        }
	        Venta venta = new Venta(id, new Date(), formaDePago);
	        venta.calcularMontoFinal();
	        return venta;
	    }
	    
	    public void calcularMontoFinal() {
	        
	    	double montoBase = 0;
	        for (DetalleVenta detalle : detalles) {
	            montoBase += detalle.getPrecio() * detalle.getCantidad();
	        }
	        montoFinal = formaDePago.calcularMontoFinal(montoBase);
	    }
	    
		

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public Pedido getPedido() {
			return pedido;
		}
		public void setPedido(Pedido pedido) {
			this.pedido = pedido;
		}
		public Date getFecha() {
			return fecha;
		}
		
		public void setFecha(Date fecha) {
			this.fecha = fecha;
		}
		
		public FormaDePago getFormaDePago() {
			return formaDePago;
		}
		public void setFormaDePago(FormaDePago formaDePago) {
			this.formaDePago = formaDePago;
		}
		public double getMontoFinal() {
			return montoFinal;
		}
		public void setMontoFinal(double montoFinal) {
			this.montoFinal = montoFinal;
		}
		public boolean isVentaDirecta() {
			return ventaDirecta;
		}
		public void setVentaDirecta(boolean ventaDirecta) {
			this.ventaDirecta = ventaDirecta;
		}

		public ArrayList<DetalleVenta> getDetalles() {
			return detalles;
		}

		public void setDetalles(ArrayList<DetalleVenta> detalles) {
			this.detalles = detalles;
		}
	    
	    
}
