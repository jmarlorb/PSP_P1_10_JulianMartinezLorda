package suplementarias;

import java.util.ArrayList;
import java.util.List;

public class Almacen {

	private List<Material> tablones;
	private List<Mueble> muebles;
	private Integer capacidadMuebles;
	
	public Almacen(int capacidad) {
		this.tablones = new ArrayList<Material>();
		this.muebles = new ArrayList<Mueble>();
		this.capacidadMuebles = capacidad;
	}

	public List<Material> getTablones() {
		return tablones;
	}

	public void setTablones(List<Material> tablones) {
		this.tablones = tablones;
	}

	public List<Mueble> getMuebles() {
		return muebles;
	}

	public void setMuebles(List<Mueble> muebles) {
		this.muebles = muebles;
	}

	public Integer getCapacidadMuebles() {
		return capacidadMuebles;
	}

	public void setCapacidadMuebles(int capacidadMuebles) {
		this.capacidadMuebles = capacidadMuebles;
	}
	
	
	
	
}
