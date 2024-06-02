package negocio;

import java.util.ArrayList;

public class Vehiculo {
	
	private String marca ;
	private ArrayList<Autoparte> listaAutopartes;
	
	public Vehiculo(String marca) {
		//super();
		this.marca = marca;
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
	
	

}
