package negocio;

import java.io.Serializable;

public abstract class FormaDePago implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public abstract double calcularMontoFinal(double montoBase);
    
}
