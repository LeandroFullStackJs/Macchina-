package negocio;

import java.util.ArrayList;

public class Vehiculo {
	
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
	
	

}
