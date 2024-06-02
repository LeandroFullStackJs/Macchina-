package negocio;
import java.util.ArrayList;
//import java.util.Date;

public class Pedido {

	private int id_Pedido; 
	private String fecha; // Podria ser Date . 
	private double montoTotal; 
	private int cantidad ;
	private int dni_cliente ; 
	//private autopartesPedidas: Autoparte[]
	private ArrayList<DetallePedido> detalles;
	private boolean cancelado ;
	
	 public void calcularMontoTotal() {
	        montoTotal = 0;
	        for (DetallePedido detalle : detalles) {
	            montoTotal += detalle.getPrecio() * detalle.getCantidad();
	        }
	 }
}
