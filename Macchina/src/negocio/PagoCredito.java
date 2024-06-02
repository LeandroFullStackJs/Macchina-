package negocio;

public class PagoCredito extends FormaDePago {
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

}
