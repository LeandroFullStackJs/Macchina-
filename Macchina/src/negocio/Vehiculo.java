package negocio;

import java.io.Serializable;
import java.util.ArrayList;

public class Vehiculo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String marca ;
	private String modelo;
	private ArrayList<Autoparte> listaAutopartes;
	
	public Vehiculo(String marca , String modelo) {
		//super();
		this.marca = marca;
		this.modelo = modelo;
		this.listaAutopartes = new ArrayList<Autoparte>();
	}
	
	public ArrayList<Autoparte> getListaAutopartes() {
		return listaAutopartes;
	}

	public void setListaAutopartes(ArrayList<Autoparte> listaAutopartes) {
		this.listaAutopartes = listaAutopartes;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("Marca: ").append(marca).append("\n");
	    sb.append("Modelo: ").append(modelo).append("\n");
	    sb.append("Autopartes:\n");
	    for (Autoparte autoparte : listaAutopartes) {
	        sb.append("  ").append(autoparte.toString()).append("\n");
	    }
	    return sb.toString();
	}

}
