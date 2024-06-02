package negocio;

import java.util.ArrayList;

public class Venta {

		private int id;
	    private Pedido pedido;
	    private String fecha;
	    private ArrayList<DetalleVenta> detalles;
	    private FormaDePago formaDePago;
	    private double montoFinal;
	    private boolean ventaDirecta;
	    
	    public Venta(int id, Pedido pedido, String fecha, ArrayList<DetalleVenta> detalles, FormaDePago formaDePago,
				double montoFinal, boolean ventaDirecta) {
			super();
			this.id = id;
			this.pedido = pedido;
			this.fecha = fecha;
			this.detalles = new ArrayList<DetalleVenta>();
			this.formaDePago = formaDePago;
			this.montoFinal = montoFinal;
			this.ventaDirecta = ventaDirecta;
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
		public String getFecha() {
			return fecha;
		}
		public void setFecha(String fecha) {
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
