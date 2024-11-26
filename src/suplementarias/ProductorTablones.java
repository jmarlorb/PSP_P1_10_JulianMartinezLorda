package suplementarias;

public class ProductorTablones extends Thread {
	
	private Almacen almacen;

	public ProductorTablones(Almacen almacen) {
		super();
		this.almacen = almacen;
	}

	
	
	public void run() {
		int tablonesIdContador = 1;
		boolean capacidadSuperada = false;
		Material aux;
		while (!capacidadSuperada) {
			synchronized(almacen.getTablones()) {
				if (almacen.getTablones().size()<5) {
					aux = new Material(tablonesIdContador);
					System.out.println("Se añade el tablón " + tablonesIdContador + " al almacén.");
					tablonesIdContador++;
					
					almacen.getTablones().add(aux);
					almacen.getTablones().notifyAll();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						almacen.getTablones().wait();
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			synchronized(almacen.getMuebles()) {
				if (almacen.getMuebles().size() >= almacen.getCapacidadMuebles()) {
					capacidadSuperada = true;
				}
			}
			
		}
		
	}
}
