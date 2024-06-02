package negocio;

public class PagoEfectivo extends FormaDePago {

	@Override
	public double calcularMontoFinal(double montoBase) {
		// TODO Auto-generated method stub
		return montoBase * 0.9;
	}

}
