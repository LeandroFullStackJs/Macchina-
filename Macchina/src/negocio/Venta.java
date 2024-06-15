package negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Venta implements Serializable {

	 private static final long serialVersionUID = 1L;
	    private int id;
	    private Pedido pedido;
	    private Date fecha;
	    private ArrayList<DetalleVenta> detalles;
	    private FormaDePago formaDePago;
	    private double montoFinal;
	    private boolean ventaDirecta;
	    private Cliente cliente; // Usar un objeto Cliente en lugar de solo un nombre
	    
	    // Constructor para ventas con pedido
	    public Venta(int id, Date fecha, FormaDePago formaDePago, Pedido pedido) {
	        this.id = id;
	        this.fecha = fecha;
	        this.detalles = new ArrayList<>();
	        this.formaDePago = formaDePago;
	        this.pedido = pedido;
	        this.ventaDirecta = false;
	        this.cliente = pedido != null ? pedido.getCliente() : null;
	        this.calcularMontoFinal();
	    }

	    // Constructor para ventas directas
	    public Venta(int id, Date fecha, FormaDePago formaDePago, Cliente cliente) {
	        this.id = id;
	        this.fecha = fecha;
	        this.detalles = new ArrayList<>();
	        this.formaDePago = formaDePago;
	        this.cliente = cliente;
	        this.ventaDirecta = true;
	        this.calcularMontoFinal();
	    }
	    public void agregarDetalle(DetalleVenta detalle) {
	        detalles.add(detalle);
	    }
// NO NECESITO ELIMINAR EL DETALLE DE VENTA PORQUE AL ELIMINAR LA VENTA TAMBIEN SE ELIMINA EL DETALLE ( RELACION DE COMPOSICION ) 
	  //  public void eliminarDetalle(int idDetalle) {     
	  //  }


	    // Métodos estáticos para crear instancias de Venta
	    public static Venta cargarVentaDirecta(int id, FormaDePago formaDePago, Cliente cliente) {
	        return new Venta(id, new Date(), formaDePago, cliente);
	    }
	    
	    public static Venta realizarVentaPedido(int id, Pedido pedido, FormaDePago formaDePago) { 
	        Venta venta = new Venta(id, new Date(), formaDePago, pedido);
	        for (DetallePedido detallePedido : pedido.getDetalles()) {
	            DetalleVenta detalleVenta = new DetalleVenta(
	                detallePedido.getId(),
	                detallePedido.getAutoparte(),
	                detallePedido.getPrecio(),
	                detallePedido.getCantidad()
	            );
	            venta.detalles.add(detalleVenta);
	        }
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
	    
	    public void agregarDetalleVenta(DetalleVenta detalle) {
	        this.detalles.add(detalle);
	        this.calcularMontoFinal();
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

	    public Cliente getCliente() {
	        return cliente;
	    }

	    public void setCliente(Cliente cliente) {
	        this.cliente = cliente;
	    }

	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        sb.append("Venta{id=").append(id)
	          .append(", fecha='").append(fecha).append('\'')
	          .append(", montoTotal=").append(montoFinal)
	          .append(", cliente=").append(cliente != null ? cliente.getNombre() : "N/A")
	          .append(", items=[");
	        
	        for (DetalleVenta detalle : detalles) {
	            sb.append("\n\t").append(detalle);
	        }
	        sb.append("\n]}");
	        return sb.toString();
	    }
		 
	    // PODRIA TENER EL CODIGO DE convertirPedidoAVenta aca , pero seria mas dificil al tener la logica de la interfaz en PedidoDialog . 
	  /*  public class Venta {
	        public static Venta convertirPedidoAVenta(Empresa empresa, Pedido pedido, FormaDePago formaDePago) throws Exception {
	            Venta venta = Venta.realizarVentaPedido(empresa.generarIdVenta(), pedido, formaDePago);
	            empresa.agregarVenta(venta);
	            empresa.cancelarPedido(pedido.getId_Pedido());
	            return venta;
	        }
	    } */
		 
}
