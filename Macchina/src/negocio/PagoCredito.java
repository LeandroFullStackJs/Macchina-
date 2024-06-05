package negocio;

import java.io.Serializable;

public class PagoCredito extends FormaDePago  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cuotas;
	 
	 public PagoCredito(int cuotas) {
	        if (cuotas == 2 || cuotas == 3 || cuotas == 6) {
	            this.cuotas = cuotas;
	        } else {
	            throw new IllegalArgumentException("Número de cuotas no válido");
	        }
	    }
	 
	@Override
	public double calcularMontoFinal(double montoBase) {
		// TODO Auto-generated method stub
		switch (cuotas) {
        case 2:
            return montoBase * 1.06;
        case 3:
            return montoBase * 1.12;
        case 6:
            return montoBase * 1.20;
        default:
            throw new IllegalArgumentException("Número de cuotas no válido");
		}
	}

	public int getCuotas() {
		return cuotas;
	}

	public void setCuotas(int cuotas) {
		if (cuotas != 2 && cuotas != 3 && cuotas != 6) {
            throw new IllegalArgumentException("Cantidad de cuotas inválida. Debe ser 2, 3 o 6.");
        }
		this.cuotas = cuotas;
	}

	 @Override
	    public String toString() {
	        return "Crédito " + cuotas + " cuotas";
	    }
	
	

}
