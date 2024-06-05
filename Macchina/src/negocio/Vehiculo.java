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
		if (marca == null || marca.trim().isEmpty() || modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("Marca y modelo no pueden ser nulos o vacíos.");
        }
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
		if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede ser nula o vacía.");
        }
		this.marca = marca;
	}

	public String getModelo() {
		
		return modelo;
	}

	public void setModelo(String modelo) {
		if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede ser nulo o vacío.");
        }
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
