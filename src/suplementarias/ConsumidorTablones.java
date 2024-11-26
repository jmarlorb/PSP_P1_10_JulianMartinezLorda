package suplementarias;

public class ConsumidorTablones extends Thread {

	private Almacen almacen;
	private IdMuebles IdMueble;
	public ConsumidorTablones(Almacen almacen, IdMuebles contadorIdMueble) {
		super();
		this.almacen = almacen;
		this.IdMueble = contadorIdMueble;
	}
	public Almacen getAlmacen() {
		return almacen;
	}
	public void setAlmacen(Almacen almacen) {
		this.almacen = almacen;
	}
	public IdMuebles getContadorIdMueble() {
		return IdMueble;
	}
	public void setContadorIdMueble(IdMuebles contadorIdMueble) {
		this.IdMueble = contadorIdMueble;
	}
	
	public void run() {
		boolean capacidadAlmacenSuperada = false;
		boolean tablonesSuficientes = false;
		Material auxiliarMaterial = null;
		Mueble auxiliarMueble;
		while (!capacidadAlmacenSuperada) {
			synchronized(this.almacen.getMuebles()){
				if (almacen.getMuebles().size() >= almacen.getCapacidadMuebles()) {
					capacidadAlmacenSuperada = true;
				} 
			}
			if (!capacidadAlmacenSuperada) {
				synchronized(this.almacen.getTablones()) {
					if (almacen.getTablones().size() >= 1) {
						auxiliarMaterial = almacen.getTablones().removeFirst();
						System.out.println("El tablón " + auxiliarMaterial.getId() + " va a ser usado como material.");
						almacen.getTablones().notifyAll();
						tablonesSuficientes = true;
					} else {
						tablonesSuficientes = false;
						try {
							almacen.getTablones().wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				if (tablonesSuficientes) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized(this.almacen.getMuebles()) {
						if (almacen.getMuebles().size() >= almacen.getCapacidadMuebles()) {
							capacidadAlmacenSuperada = true;
							if (auxiliarMaterial!=null)System.out.println("Capacidad superada. El tablón " +auxiliarMaterial.getId() + " no se usara como material");
							almacen.getMuebles().notifyAll();
						} else {
							IdMueble.setContador(IdMueble.getContador()+1);
							auxiliarMueble = new Mueble(IdMueble.getContador());
							almacen.getMuebles().add(auxiliarMueble);
							System.out.println("El mueble " + IdMueble.getContador() + " ha sido fabricado y añadido al almacen.");
							almacen.getMuebles().notifyAll();
						}
						
						
					}
				}
				
			}
			
		}
		
	}
}
