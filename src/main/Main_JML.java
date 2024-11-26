package main;

import suplementarias.Almacen;
import suplementarias.ConsumidorTablones;
import suplementarias.IdMuebles;
import suplementarias.ProductorTablones;

public class Main_JML {

	public static void main(String[] args) {
		int capacidalAlmacen = 10;
		Almacen almacen = new Almacen(capacidalAlmacen);
		IdMuebles idMuebles = new IdMuebles(0);
		ProductorTablones pt = new ProductorTablones(almacen);
		ConsumidorTablones ct1 = new ConsumidorTablones(almacen, idMuebles);
		ConsumidorTablones ct2 = new ConsumidorTablones(almacen, idMuebles);
		ConsumidorTablones ct3 = new ConsumidorTablones(almacen, idMuebles);
		
		pt.start();
		ct1.start();
		ct2.start();
		ct3.start();
		
		try {
			pt.join();
			ct1.join();
			ct2.join();
			ct3.join();
			System.out.println("TERMINADO");
			System.out.println(idMuebles.getContador());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
