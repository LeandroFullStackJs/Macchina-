package negocio;

import java.io.Serializable;

public class PagoDebito extends FormaDePago implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public double calcularMontoFinal(double montoBase) {
		// TODO Auto-generated method stub
		return montoBase;
	}

}
