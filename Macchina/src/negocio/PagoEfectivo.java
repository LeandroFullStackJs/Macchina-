package negocio;

import java.io.Serializable;

public class PagoEfectivo extends FormaDePago  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public double calcularMontoFinal(double montoBase) {
		// TODO Auto-generated method stub
		return montoBase * 0.9;
	}
	
	 public String toString() {
	        return " Pago En Efectivo" ;
	    }

}
