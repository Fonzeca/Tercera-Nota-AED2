package visual.threads;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import estructura.Codificador;
import visual.VentanaComprimir;

public class ThreadComprimir extends Thread {
	private String ruta;
	private VentanaComprimir venCom;
	public ThreadComprimir(String ruta, VentanaComprimir venCom) {
		this.ruta = ruta;
		this.venCom = venCom;
	}
	public void run(){
		try {
			Codificador codificador = new Codificador(new RandomAccessFile(ruta, "rw"), ruta,venCom);
			codificador.comprimir();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
