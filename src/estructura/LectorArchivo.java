package estructura;

import java.io.RandomAccessFile;

public class LectorArchivo {
	
	private RandomAccessFile raf;
	private Lista lista;
	
	
	public LectorArchivo(String ruta) {
		try {
			raf= new RandomAccessFile(ruta, "rw");
		} catch (Exception e) {
			System.err.println("No se encontró archivo.");
		}
		lista=new Lista();
	}
	public void leerArchivo(){
		try {
			for (int i = 0; i < raf.length(); i++) {
				lista.insertar(raf.readByte());
			}
		} catch (Exception e) {
			System.err.println("Archivo no encontrado.");
		}
		lista.reordenar();
	}
	public void mostrar(){
		lista.mostrar();
	}
	public void mostrarOrdenado(){
		lista.reordenar();
		lista.mostrar();
	}
	public Lista getLista(){
		return lista;
	}
	public RandomAccessFile getRaf() {
		return raf;
	}

}
